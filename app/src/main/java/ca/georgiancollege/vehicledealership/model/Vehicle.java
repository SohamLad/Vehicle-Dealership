package ca.georgiancollege.vehicledealership.model;

public class Vehicle{
    public String id;
    public String make;
    public String model;
    public String condition;
    public String engine;
    public String year;
    public String numberofdoors;
    public String price;
    public String color;
    public String image;
    public String solddate;

    public Vehicle(String id, String make, String model, String condition, String engine, String year, String numberofdoors, String price, String color, String image, String solddate) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.condition = condition;
        this.engine = engine;
        this.year = year;
        this.numberofdoors = numberofdoors;
        this.price = price;
        this.color = color;
        this.image = image;
        this.solddate = solddate;
    }

    public Vehicle() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumberofdoors() {
        return numberofdoors;
    }

    public void setNumberofdoors(String numberofdoors) {
        this.numberofdoors = numberofdoors;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSolddate() {
        return solddate;
    }

    public void setSolddate(String solddate) {
        this.solddate = solddate;
    }

    public Object writeToFile() {

        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%n", id,make,model,condition,engine,year,numberofdoors,price,color,image,solddate);
    }

    public String getJsonData() {
        return null;
    }
}
