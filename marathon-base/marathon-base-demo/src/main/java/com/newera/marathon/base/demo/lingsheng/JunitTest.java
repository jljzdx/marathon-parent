package com.newera.marathon.base.demo.lingsheng;


import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class JunitTest {

    @Test
    public void test_sortByName(){
        Extension extension = new Extension();
        extension.setFirstName("wang");
        extension.setLastName("weibin");
        extension.setExt("1");
        extension.setExtType("User");
        Extension extension1 = new Extension();
        extension1.setFirstName("wang");
        extension1.setLastName("nihao");
        extension1.setExt("2");
        extension1.setExtType("TMO");
        Extension extension2 = new Extension();
        extension2.setFirstName("wang");
        extension2.setLastName("hello");
        extension2.setExt("3");
        extension2.setExtType("Dept");
        Extension extension3 = new Extension();
        extension3.setFirstName("li");
        extension3.setLastName("weibin");
        extension3.setExt("1");
        extension3.setExtType("AO");
        Extension extension4 = new Extension();
        extension4.setFirstName("li");
        extension4.setLastName("nihao");
        extension4.setExt("2");
        extension4.setExtType("TMO");
        Extension extension5 = new Extension();
        extension5.setFirstName("zhang");
        extension5.setLastName("nihao");
        extension5.setExt("1");
        extension5.setExtType("Other");

        List<Extension> extensions = new ArrayList<>();
        extensions.add(extension);
        extensions.add(extension1);
        extensions.add(extension2);
        extensions.add(extension3);
        extensions.add(extension4);
        extensions.add(extension5);
        extensions = Utils.sortByName(extensions);
        extensions.forEach(System.out::println);
    }
    @Test
    public void test_sortByExtType(){
        Extension extension = new Extension();
        extension.setFirstName("wang");
        extension.setLastName("weibin");
        extension.setExt("1");
        extension.setExtType("User");
        Extension extension1 = new Extension();
        extension1.setFirstName("wang");
        extension1.setLastName("nihao");
        extension1.setExt("2");
        extension1.setExtType("TMO");
        Extension extension2 = new Extension();
        extension2.setFirstName("wang");
        extension2.setLastName("hello");
        extension2.setExt("3");
        extension2.setExtType("Dept");
        Extension extension3 = new Extension();
        extension3.setFirstName("li");
        extension3.setLastName("weibin");
        extension3.setExt("1");
        extension3.setExtType("AO");
        Extension extension4 = new Extension();
        extension4.setFirstName("li");
        extension4.setLastName("nihao");
        extension4.setExt("2");
        extension4.setExtType("TMO");
        Extension extension5 = new Extension();
        extension5.setFirstName("zhang");
        extension5.setLastName("nihao");
        extension5.setExt("1");
        extension5.setExtType("Other");

        List<Extension> extensions = new ArrayList<>();
        extensions.add(extension);
        extensions.add(extension1);
        extensions.add(extension2);
        extensions.add(extension3);
        extensions.add(extension4);
        extensions.add(extension5);
        //sort by User > Dept > AO > TMO > Other;
        extensions = Utils.sortByExtType(extensions);
        extensions.forEach(System.out::println);
    }
    @Test
    public void test_sumByQuarter(){
        SaleItem saleItem = new SaleItem();
        saleItem.setDate(new Date());
        saleItem.setMonth(1);
        saleItem.setSaleNumbers(11000);
        saleItem.setTransationId("123456");
        SaleItem saleItem1 = new SaleItem();
        saleItem1.setDate(new Date());
        saleItem1.setMonth(2);
        saleItem1.setSaleNumbers(21000);
        saleItem1.setTransationId("123456");
        SaleItem saleItem2 = new SaleItem();
        saleItem2.setDate(new Date());
        saleItem2.setMonth(2);
        saleItem2.setSaleNumbers(22000);
        saleItem2.setTransationId("123456");
        SaleItem saleItem3 = new SaleItem();
        saleItem3.setDate(new Date());
        saleItem3.setMonth(3);
        saleItem3.setSaleNumbers(31000);
        saleItem3.setTransationId("123456");
        SaleItem saleItem4 = new SaleItem();
        saleItem4.setDate(new Date());
        saleItem4.setMonth(4);
        saleItem4.setSaleNumbers(41000);
        saleItem4.setTransationId("123456");
        SaleItem saleItem5 = new SaleItem();
        saleItem5.setDate(new Date());
        saleItem5.setMonth(4);
        saleItem5.setSaleNumbers(42000);
        saleItem5.setTransationId("123456");
        SaleItem saleItem6 = new SaleItem();
        saleItem6.setDate(new Date());
        saleItem6.setMonth(11);
        saleItem6.setSaleNumbers(111000);
        saleItem6.setTransationId("123456");

        List<SaleItem> saleItems = new ArrayList<>();
        saleItems.add(saleItem);
        saleItems.add(saleItem1);
        saleItems.add(saleItem2);
        saleItems.add(saleItem3);
        saleItems.add(saleItem4);
        saleItems.add(saleItem5);
        saleItems.add(saleItem6);
        //第一季度[1,2,3]，第二季度[4,5,6]，第三季度[7,8,9]，第四季度[10,11,12]
        List<QuarterSalesItem> quarterSalesItems = Utils.sumByQuarter(saleItems);
        double total = quarterSalesItems.stream().filter(w->w.getQuarter()==1).collect(Collectors.toList()).get(0).getTotal();
        Assert.assertEquals(85000,total,0);
    }
    @Test
    public void test_maxByQuarter(){

        SaleItem saleItem = new SaleItem();
        saleItem.setDate(new Date());
        saleItem.setMonth(1);
        saleItem.setSaleNumbers(11000);
        saleItem.setTransationId("123456");
        SaleItem saleItem1 = new SaleItem();
        saleItem1.setDate(new Date());
        saleItem1.setMonth(2);
        saleItem1.setSaleNumbers(21000);
        saleItem1.setTransationId("123456");
        SaleItem saleItem2 = new SaleItem();
        saleItem2.setDate(new Date());
        saleItem2.setMonth(2);
        saleItem2.setSaleNumbers(22000);
        saleItem2.setTransationId("123456");
        SaleItem saleItem3 = new SaleItem();
        saleItem3.setDate(new Date());
        saleItem3.setMonth(3);
        saleItem3.setSaleNumbers(31000);
        saleItem3.setTransationId("123456");
        SaleItem saleItem4 = new SaleItem();
        saleItem4.setDate(new Date());
        saleItem4.setMonth(4);
        saleItem4.setSaleNumbers(41000);
        saleItem4.setTransationId("123456");
        SaleItem saleItem5 = new SaleItem();
        saleItem5.setDate(new Date());
        saleItem5.setMonth(4);
        saleItem5.setSaleNumbers(42000);
        saleItem5.setTransationId("123456");
        SaleItem saleItem6 = new SaleItem();
        saleItem6.setDate(new Date());
        saleItem6.setMonth(11);
        saleItem6.setSaleNumbers(111000);
        saleItem6.setTransationId("123456");

        List<SaleItem> saleItems = new ArrayList<>();
        saleItems.add(saleItem);
        saleItems.add(saleItem1);
        saleItems.add(saleItem2);
        saleItems.add(saleItem3);
        saleItems.add(saleItem4);
        saleItems.add(saleItem5);
        saleItems.add(saleItem6);


        List<Integer> monthList = saleItems.stream().filter(x->x.getSaleNumbers()>4000).map(w->w.getMonth()).collect(Collectors.toList());
        monthList.forEach(System.out::println);







        List<QuarterSalesItem> quarterSalesItems = Utils.maxByQuarter(saleItems);
        double maxTotal = quarterSalesItems.get(0).getTotal();
        Assert.assertEquals(111000,maxTotal,0);
    }
    @Test
    public void test_getUnUsedKeys(){
        int[] allKeys = {0,1,2,3,4,5,6,7,8,9};
        int[] usedKeys = {2,4,7};
        int[] unusedKeys = Utils.getUnUsedKeys(allKeys,usedKeys);
        String result = Arrays.stream(unusedKeys).boxed().map(w->w+"").collect(Collectors.joining(",","[","]"));
        System.out.println(result);
        int[] expecteds = {0,1,3,5,6,8,9};
        Assert.assertArrayEquals(expecteds,unusedKeys);
    }
}
