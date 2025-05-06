class Employee {

    public int id;
    public String name;
    public String position;
    public double salary;
    public String username;
    public String password;

    public Employee(int id, String name, String position, double salary, String username, String password) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.username = username;
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", position=" + position + ", salary=" + salary + "]";
    }

}
