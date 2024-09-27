package model;

public class Cooler {
    private int id;
    private String name;
    private double rating;
    private double price;
    private String shop_URL;
    private String image_URL;
    private int tdp;
    private String socket;
    private int rpm;
    private int noise_level;
    private int radiator_size;
    private int cooler_height;

    public Cooler() {
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

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public int getNoise_level() {
        return noise_level;
    }

    public void setNoise_level(int noise_level) {
        this.noise_level = noise_level;
    }

    public int getRadiator_size() {
        return radiator_size;
    }

    public void setRadiator_size(int radiator_size) {
        this.radiator_size = radiator_size;
    }

    public int getCooler_height() {
        return cooler_height;
    }

    public void setCooler_height(int cooler_height) {
        this.cooler_height = cooler_height;
    }
}
