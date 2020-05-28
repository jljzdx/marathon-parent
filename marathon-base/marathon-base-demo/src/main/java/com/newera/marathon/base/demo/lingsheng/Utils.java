package com.newera.marathon.base.demo.lingsheng;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

	//Question1
	public static List<Extension> sortByName(List<Extension> extensions) {
		extensions = extensions.stream().sorted(
				Comparator.comparing(Extension::getFirstName)
						.thenComparing(Extension::getLastName)).collect(Collectors.toList());
		return extensions;
	}
	
	//Question2
	public static List<Extension> sortByExtType(List<Extension> extensions) {
		//定义类型的优先级
		Map<String,Integer> map = new HashMap<>();
		map.put("User",1);
		map.put("Dept",2);
		map.put("AO",3);
		map.put("TMO",4);
		map.put("Other",5);
		//根据优先级进行比较
		Comparator<Extension> comparator = (h1, h2) -> {
			Integer index = map.get(h1.getExtType());
			Integer index2 = map.get(h2.getExtType());
			return index.compareTo(index2);
		};
		extensions = extensions.stream().sorted(comparator).collect(Collectors.toList());
		return extensions;
	}
	
	//Question3
	public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {
		Map<Integer, List<SaleItem>> totalAgeByGender = saleItems.stream().collect(Collectors.groupingBy(w->{
			//定义各个月份属于哪个季度
			Map<Integer,Integer> map = new HashMap();
			map.put(1,1);
			map.put(2,1);
			map.put(3,1);
			map.put(4,2);
			map.put(5,2);
			map.put(6,2);
			map.put(7,3);
			map.put(8,3);
			map.put(9,3);
			map.put(10,4);
			map.put(11,4);
			map.put(12,4);
			//根据什么规则分组
			Integer result = map.get(w.getMonth());
			return result;
		}));
		List<QuarterSalesItem> list = new ArrayList<>();
		totalAgeByGender.forEach((k,v)->{
			QuarterSalesItem qs = new QuarterSalesItem();
			qs.setQuarter(k);
			qs.setTotal(v.stream().mapToDouble(SaleItem::getSaleNumbers).sum());
			list.add(qs);
		});
		return list;
	}
	
    //Question4
	public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
		List<QuarterSalesItem> result = sumByQuarter(saleItems);
		QuarterSalesItem qs = result.stream().max((u1, u2) -> Double.valueOf(u1.getTotal()).compareTo(Double.valueOf(u2.getTotal()))).get();
		result.clear();
		result.add(qs);
		return result;
	}
    
	//Question5
	/**
	 * We have all Keys: 0-9;
	 * usedKeys is an array to store all used keys like :[2,3,4];
	 * We want to get all unused keys, in this example it would be: [0,1,5,6,7,8,9,]
	 */
	
	public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {
		int[] unusedKeys = new int[allKeys.length-usedKeys.length];
		int index = 0;
		//排序并去重
		int[] all = sortAndRepeat(allKeys);
		int[] used = sortAndRepeat(usedKeys);
		//获取最大和最小的索引
		int min = used[0];
		int max = used[used.length-1];
		int minIndex = getIndex(all,min,1);
		int maxIndex = getIndex(all,max,2);
		for (int i = 0; i < minIndex; i++){
			unusedKeys[index] = all[i];
			index ++;
		}
		for (int i = minIndex+1; i < maxIndex; i++){
			int flag = 1;
			for (int j = 1; j < used.length-1; j++){
				if(all[i] == used[j]){
					flag = 0;
					break;
				}
			}
			if(flag == 1){
				unusedKeys[index] = all[i];
				index ++;
			}
		}
		for (int i = maxIndex+1; i < all.length; i++){
			unusedKeys[index] = all[i];
			index ++;
		}
		return unusedKeys;
	}

	/**
	 * 获取最大值和最小值在all数组中的索引
	 * @param array allKeys
	 * @param value 最大值或最小值
	 * @param type 类型：1/2：最小值/最大值
	 * @return
	 */
	public static int getIndex(int[] array, int value, int type){
		int index = 0;
		if(type == 1){//获取最小值的索引
			for(int i = 0; i < array.length; i++){
				if(array[i] == value){
					index = i;
					break;
				}
			}
		}else{//获取最大值的索引
			for(int i = array.length-1; i >= 0; i--){
				if(array[i] == value){
					index = i;
					break;
				}
			}
		}
		return index;
	}

	/**
	 * 排序并去重
	 * @param array 任意一个数组
	 * @return
	 */
	public static int[] sortAndRepeat(int[] array){
		HashSet<Integer> hashset = new HashSet<Integer>();//去重
		for (int i = 0;i<array.length;i++){
			hashset.add(array[i]);
		}
		TreeSet<Integer> treeSet = new TreeSet<Integer>(hashset);//排序
		Integer [] integers = treeSet.toArray(new Integer[]{});//转成数组
		int [] ints = new int[integers.length];
		for (int i = 0;i<integers.length;i++){
			ints[i] = integers[i].intValue();
		}
		return ints;
	}
	
}
