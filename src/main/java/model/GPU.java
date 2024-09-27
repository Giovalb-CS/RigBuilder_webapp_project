package model;

public class GPU {
    private int id;
    private String name;
    private double rating;
    private double price;
    private String shop_URL;
    private String image_URL;
    private int tdp;
    private String memory;
    private int memory_clock;
    private int core_clock;
    private int boost_clock;
    private int lenght;
    private int slot_width;
    private String power_cable;

    public GPU() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShop_URL() {
        return shop_URL;
    }

    public void setShop_URL(String shop_URL) {
        this.shop_URL = shop_URL;
    }

    public String getImage_URL() {
        return image_URL;
    }

    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public int getMemory_clock() {
        return memory_clock;
    }

    public void setMemory_clock(int memory_clock) {
        this.memory_clock = memory_clock;
    }

    public int getCore_clock() {
        return core_clock;
    }

    public void setCore_clock(int core_clock) {
        this.core_clock = core_clock;
    }

    public int getBoost_clock() {
        return boost_clock;
    }

    public void setBoost_clock(int boost_clock) {
        this.boost_clock = boost_clock;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getSlot_width() {
        return slot_width;
    }

    public void setSlot_width(int slot_width) {
        this.slot_width = slot_width;
    }

    public String getPower_cable() {
        return power_cable;
    }

    public void setPower_cable(String power_cable) {
        this.power_cable = power_cable;
    }
}
