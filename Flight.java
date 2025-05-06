class Flight {

    private int id;
    private String flightNumber;
    private String source;
    private String deatination;
    private String departureTime;
    private String arrivalTime;
    private int seats;
    private String flightClass;
    private String food;
    private String landingtime;
    int w_seats, e_seats, b_seats;
    double w_price, e_price, b_price;
    String category;
    int avaible_seat;

    public Flight(int id, String flightNumber, String source, String destination, String departureTime,
            String arrivalTime, int seats, String food, String landingtime, int w_seats, int e_seats, int b_seats,
            double w_price, double e_price, double b_price) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.source = source;
        this.deatination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.food = food;
        this.landingtime = landingtime;
        this.w_price = w_price;
        this.e_price = e_price;
        this.b_price = b_price;
        this.w_seats = w_seats;
        this.e_seats = e_seats;
        this.b_seats = b_seats;

    }

    Flight() {

    }

    public int getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDeatination() {
        return deatination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getSeats() {
        return seats;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public String getFood() {
        return food;
    }

    public String getLandingtime() {
        return landingtime;
    }

    public int getW_seats() {
        return w_seats;
    }

    public int getE_seats() {
        return e_seats;
    }

    public int getB_seats() {
        return b_seats;
    }

    public double getW_price() {
        return w_price;
    }

    public double getE_price() {
        return e_price;
    }

    public double getB_price() {
        return b_price;
    }

    public String getCategory() {
        return category;
    }

    public int getAvaible_seat() {
        return avaible_seat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDeatination(String deatination) {
        this.deatination = deatination;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setLandingtime(String landingtime) {
        this.landingtime = landingtime;
    }

    public void setW_seats(int w_seats) {
        this.w_seats = w_seats;
    }

    public void setE_seats(int e_seats) {
        this.e_seats = e_seats;
    }

    public void setB_seats(int b_seats) {
        this.b_seats = b_seats;
    }

    public void setW_price(double w_price) {
        this.w_price = w_price;
    }

    public void setE_price(double e_price) {
        this.e_price = e_price;
    }

    public void setB_price(double b_price) {
        this.b_price = b_price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvaible_seat(int avaible_seat) {
        this.avaible_seat = avaible_seat;
    }

}
