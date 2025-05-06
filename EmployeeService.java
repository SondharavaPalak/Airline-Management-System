import java.sql.*;

class EmployeeService {
    BST obj = new BST();
    int emp_id;

    EmployeeService() {
        add_the_emp();
    }

    public boolean addEmployee(String name, String position, double salary, String username, String password) {
        String query = "INSERT INTO employees (e_name, position, salary,e_username,e_password) VALUES (?, ?, ?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setDouble(3, salary);
            stmt.setString(4, username);
            stmt.setString(5, password);
            int rowsInserted = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rowsInserted > 0) {
                if (rs.next()) {
                    emp_id = rs.getInt(1);
                    obj.insert(new Employee(emp_id, name, position, salary, username, password));
                    conn.commit();
                }
            }
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("\t\tNew Added Employee Id is : " + emp_id);
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean deleteEmployee(int employeeId) {
        if (obj.search(employeeId) != null) {
            String query = "DELETE FROM employees WHERE e_id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                conn.setAutoCommit(false);
                stmt.setInt(1, employeeId);
                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    obj.delete(employeeId);
                    conn.commit();
                }

                return rowsDeleted > 0;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean updateEmployee(int employeeId, String name, String position, double salary) {
        if (obj.search(employeeId) != null) {
            String query = "UPDATE employees SET e_name = ?, position = ? , salary = ? WHERE e_id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, position);
                stmt.setDouble(3, salary);
                stmt.setInt(4, employeeId);
                int rowsUpdated = stmt.executeUpdate();
                obj.editEmployee(employeeId, name, position, salary);
                return rowsUpdated > 0;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }

        } else {
            return false;
        }

    }

    public void add_the_emp() {
        obj = new BST();
        String query = "SELECT * FROM employees";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("e_id");
                String name = rs.getString("e_name");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");
                obj.insert(
                        new Employee(id, name, position, salary, rs.getString("e_username"),
                                rs.getString("e_password")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void display_emp() {
        obj.inOrder();
    }
}

class BSTNode {
    Employee data;
    BSTNode left, right;

    public BSTNode(Employee data) {
        this.data = data;
        left = right = null;
    }
}

class BST {
    public BSTNode root;

    public BST() {
        root = null;
    }

    // Insert a new employee
    public void insert(Employee emp) {
        root = insertRec(root, emp);
    }

    public BSTNode insertRec(BSTNode root, Employee emp) {
        if (root == null) {
            root = new BSTNode(emp);
            return root;
        }

        if (emp.id < root.data.id) {
            root.left = insertRec(root.left, emp);
        } else if (emp.id > root.data.id) {
            root.right = insertRec(root.right, emp);
        }

        return root;
    }

    // Search for an employee by ID
    public Employee search(int id) {
        BSTNode node = searchRec(root, id);
        return (node != null) ? node.data : null;
    }

    public BSTNode searchRec(BSTNode root, int id) {
        if (root == null || root.data.id == id) {
            return root;
        }

        if (id < root.data.id) {
            return searchRec(root.left, id);
        }

        return searchRec(root.right, id);
    }

    // In-order traversal to display employees
    public void inOrder() {
        System.out.println("----------------------------------------------------------------------------------------");
        inOrderRec(root);
        System.out
                .println("-------------------------------------------------------------------------------------------");
    }

    public void inOrderRec(BSTNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.data);
            inOrderRec(root.right);
        }
    }

    public boolean editEmployee(int id, String newName, String newPosition, double salary) {
        BSTNode node = searchRec(root, id);
        if (node != null) {
            node.data.name = newName;
            node.data.position = newPosition;
            node.data.salary = salary;
            return true;
        }
        return false;
    }

    public void delete(int id) {
        root = deleteRec(root, id);
    }

    public BSTNode deleteRec(BSTNode root, int id) {
        if (root == null) {
            return root;
        }

        // Traverse the tree to find the node to be deleted
        if (id < root.data.id) {
            root.left = deleteRec(root.left, id);
        } else if (id > root.data.id) {
            root.right = deleteRec(root.right, id);
        } else {
            // Node to be deleted found

            // Case 1: Node has no children (leaf node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node has only one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has two children
            // Find the in-order successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the in-order successor
            root.right = deleteRec(root.right, root.data.id);
        }

        return root;
    }

    public Employee minValue(BSTNode root) {
        Employee minv = root.data;
        while (root.left != null) {
            root = root.left;
            minv = root.data;
        }
        return minv;
    }
}
