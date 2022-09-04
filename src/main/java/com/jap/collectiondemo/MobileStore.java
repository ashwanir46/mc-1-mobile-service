package com.jap.collectiondemo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MobileStore {


    private List<Mobile> allMobiles;
    private String record = "";
    private String splitBy = ",";

    public MobileStore() {

        allMobiles = new ArrayList<>();
    }

    public static void main(String[] args) {

        MobileStore object = new MobileStore();
        String fileName = "mobile.csv";
        System.out.println("++++++++++++++++ mobile data ++++++++++++++++++");
        System.out.println(object.readMobileData(fileName));
        System.out.println("\n++++++++++++++++ Finding phones by Brand ++++++++++++++++++++");
        System.out.println(object.findPhoneByBrand("Samsung"));
        System.out.println("\n+++++++++++++++++ Finding phones which are costlier than 500 ++++++++++++++");
        System.out.println(object.findPhoneCostMoreThan$500());
        System.out.println("\n++++++++++++++++++++++Finding phones which have camera pixels more than 12 MP +++++++++++++");
        System.out.println(object.findPhonePixelMoreThan12MP());
    }

    //Write logic to read the fileName that is "mobile.csv"
    //read all the lines one by one
    //Create Mobile class object and store data from file in the respective attributes of Mobile class
    // ex - Store brandName - Samsung in  mobile.setBrandName(brandName);
    //add mobile object in the List object and return the List
    //handle all the exceptions
    public List<Mobile> readMobileData(String fileName) {
        int countLines = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(splitBy);
                String brandName = values[0];
                String modelName = values[1];
                double cost = Double.parseDouble(values[2]);
                String screenSize = values[3];
                String batteryLife = values[4];
                String storageSpace = values[5];
                int cameraPixels = Integer.parseInt(values[6]);

                allMobiles.add(new Mobile(brandName, modelName, cost, screenSize, batteryLife, storageSpace, cameraPixels));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMobiles;
    }

    //Iterate the List created in the above method and retrieve the bandName
    //Return the List with specific Mobile having brandName coming from method parameter
    public List<Mobile> findPhoneByBrand(String brandName) {
        List<Mobile> mobilesByBrand = new ArrayList<>();
        Iterator<Mobile> iterator = allMobiles.iterator();
        while (iterator.hasNext()) {
            Mobile obj = iterator.next();
            if (obj.getBrandName().equals(brandName)) {
                mobilesByBrand.add(obj);
            }
        }
        return mobilesByBrand;
    }

    //Iterate through the List created in the first method
    //Return the List of Mobile whose cost is more than $500
    public List<Mobile> findPhoneCostMoreThan$500() {
        List<Mobile> mobilesMoreThan500 = new ArrayList<>();
        Iterator<Mobile> iterator = allMobiles.iterator();
        while (iterator.hasNext()) {
            Mobile obj = iterator.next();
            if (obj.getCost() > 500) {
                mobilesMoreThan500.add(obj);
            }
        }
        return mobilesMoreThan500;
    }

    //Iterate through the List created in the first method
    //Return the List of Mobile whose Pixel is more than 12MP
    public List<Mobile> findPhonePixelMoreThan12MP() {
        List<Mobile> mobilesMoreThan12MP = new ArrayList<>();
        Iterator<Mobile> iterator = allMobiles.iterator();
        while (iterator.hasNext()) {
            Mobile obj = iterator.next();
            if (obj.getCameraPixels() > 12) {
                mobilesMoreThan12MP.add(obj);
            }
        }
        return mobilesMoreThan12MP;
    }
}

