/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SectionsPackage;

/**
 *
 * @author user-ubunto
 */
public class Place extends Property{
    private int numberHouses;
    private int numberHotels;
    private double stayCost;
    private double stayCostPerHouse;
    private double stayCostPerHotel;
    private double priceHouse;
    private double priceHotel;
    private String groupPlace;

    public Place(int numberHouses, int numberHotels, double stayCost, double stayCostPerHouse, double stayCostPerHotel, double priceHouse, double priceHotel,String groupPlace, String name, String owner, double purchasePrice, double mortgagePrice, boolean condition, int rowposition, int columnPosition) {
        super(name, owner, purchasePrice, mortgagePrice, condition, rowposition, columnPosition);
        this.numberHouses = numberHouses;
        this.numberHotels = numberHotels;
        this.stayCost = stayCost;
        this.stayCostPerHouse = stayCostPerHouse;
        this.stayCostPerHotel = stayCostPerHotel;
        this.priceHouse = priceHouse;
        this.priceHotel = priceHotel;
        this.groupPlace = groupPlace;
    }

    public String getGroupPlace() {
        return groupPlace;
    }

    public double getStayCostPerHouse() {
        return stayCostPerHouse;
    }

    public double getStayCostPerHotel() {
        return stayCostPerHotel;
    }   
    
    public double getStayCost() {
        return (this.stayCost + (this.numberHotels*this.stayCostPerHotel) + (this.numberHouses*this.stayCostPerHouse));
    }

    public int getNumberHouses() {
        return numberHouses;
    }

    public int getNumberHotels() {
        return numberHotels;
    }

    public double getPriceHouse() {
        return priceHouse;
    }

    public double getPriceHotel() {
        return priceHotel;
    }

    public void setNumberHouses(int numberHouses) {
        this.numberHouses = numberHouses;
    }

    public void setNumberHotels(int numberHotels) {
        this.numberHotels = numberHotels;
    }
    
    public double getPriceHousesHotels(){
        return ((this.numberHotels*this.stayCostPerHotel) + (this.numberHouses*this.stayCostPerHouse));
    }
    
    
}
