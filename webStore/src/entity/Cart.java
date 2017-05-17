package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 购物车类
 * @author Administrator
 *
 */
public class Cart {

	/**
	 * 购物车属性
	 */
	private HashMap<Items, Integer> goods;//购物车的物品
	
	private double totalPrice;//购物车的总价格

	/*
	 * 构造方法
	 */
	public Cart() {
		
		goods=new HashMap<Items, Integer>();
		totalPrice=0.0;		
	}
	
	public HashMap<Items, Integer> getGoods() {
		return goods;
	}

	public void setGoods(HashMap<Items, Integer> goods) {
		this.goods = goods;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/**
	 * 向购物车添加商品
	 */
	public boolean addGoods(Items item,Integer number){
		
		if(goods.containsKey(item))
		{
			goods.put(item, goods.get(item)+number);
		}
		else
		{
			goods.put(item, number);	
		}
		calTotalPrice();
		return true;
		
	}
	/**
	 * 从购物车移除商品
	 */
	public boolean removeGoods(Items item){

		goods.remove(item);
		calTotalPrice();
        return true;
	}
	/**
	 * 计算购物车的总金额
	 */
	public double calTotalPrice(){
		
		double sum=0.0;
		Set<Items> keys=goods.keySet();//获取物品的 键
		Iterator<Items> it =keys.iterator();//通过键获取迭代器
		
		while(it.hasNext()){
			
			Items items= it.next();//计算商品的数量
			sum +=items.getPrice() * goods.get(items);
		}
		this.setTotalPrice(sum)	;
		return this.getTotalPrice();	
	}
	
	public static void main(String[] args) {
		
		Items items1=new Items(1,"鞋子","江西",200,300,"002.jpg");
		Items items2=new Items(2,"上衣","江西",300,300,"001.jpg");
		Items items3=new Items(1,"鞋子","江西",200,300,"002.jpg");
		Cart cart =new Cart();
		cart.addGoods(items1,2);
		cart.addGoods(items2,3);
		cart.addGoods(items3,3);
		//再次添加商品
//		cart.addGoods(items1,1);
//		cart.removeGoods(items2);
		
	    Set<Entry<Items, Integer>> Entries= cart.getGoods().entrySet();//获取集合的键
	    for (Entry<Items, Integer> entry : Entries) {
	    	System.out.println(entry);
		}
		
		System.out.println("总金额"+cart.calTotalPrice());
		
	}
}
