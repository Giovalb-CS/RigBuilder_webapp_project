package model;

import java.util.Objects;

public class Processor {
    private int id;
    private String name;
    private double rating;
    private double price;
    private String shop_URL;
    private String image_URL;
    private int tdp;
    private String socket;
    private String ram_type;
    private int core;
    private int thread;
    private double clock_base;
    private double clock_boost;
    private int cache;
    private int scale;
    private String generation;

    public Processor() {
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

    public String getRam_type() {
        return ram_type;
    }

    public void setRam_type(String ram_type) {
        this.ram_type = ram_type;
    }

    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public double getClock_base() {
        return clock_base;
    }

    public void setClock_base(double clock_base) {
        this.clock_base = clock_base;
    }

    public double getClock_boost() {
        return clock_boost;
    }

    public void setClock_boost(double clock_boost) {
        this.clock_boost = clock_boost;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Processor)) return false;
        Processor processor = (Processor) o;
        return Double.compare(getRating(), processor.getRating()) == 0 && Double.compare(getPrice(), processor.getPrice()) == 0 && getTdp() == processor.getTdp() && getCore() == processor.getCore() && getThread() == processor.getThread() && Double.compare(getClock_base(), processor.getClock_base()) == 0 && Double.compare(getClock_boost(), processor.getClock_boost()) == 0 && getCache() == processor.getCache() && getScale() == processor.getScale() && Objects.equals(getName(), processor.getName()) && Objects.equals(getShop_URL(), processor.getShop_URL()) && Objects.equals(getImage_URL(), processor.getImage_URL()) && Objects.equals(getSocket(), processor.getSocket()) && Objects.equals(getRam_type(), processor.getRam_type()) && Objects.equals(getGeneration(), processor.getGeneration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRating(), getPrice(), getShop_URL(), getImage_URL(), getTdp(), getSocket(), getRam_type(), getCore(), getThread(), getClock_base(), getClock_boost(), getCache(), getScale(), getGeneration());
    }

    @Override
    public String toString() {
        return "Processor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", shop_URL='" + shop_URL + '\'' +
                ", image_URL='" + image_URL + '\'' +
                ", tdp=" + tdp +
                ", socket='" + socket + '\'' +
                ", ram_type='" + ram_type + '\'' +
                ", core=" + core +
                ", thread=" + thread +
                ", clock_base=" + clock_base +
                ", clock_boost=" + clock_boost +
                ", cache=" + cache +
                ", scale=" + scale +
                ", generation='" + generation + '\'' +
                '}';
    }
}
