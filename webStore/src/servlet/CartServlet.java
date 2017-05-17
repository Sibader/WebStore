package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.regexp.internal.recompile;

import dao.ItemsDao;
import entity.Cart;
import entity.Items;

public class CartServlet extends HttpServlet {

	private String action;//用于获取传输来的动作指令 add show remove
	private ItemsDao idao =new ItemsDao();//用来获取逻辑层中的方法 getItemsById(int id)
	
	/**
	 * Constructor of the object.
	 */
	
	public CartServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(request.getParameter("action")!=null){
			this.action=request.getParameter("action");
			
			if(action.equals("add")){
				if(addToCart(request,response))
				{
					request.getRequestDispatcher("/success.jsp").forward(request, response);
				}
				else
				{
					request.getRequestDispatcher("/failure.jsp").forward(request, response);
				}
			}
			if(action.equals("show"))//如果是显示购物车
			{
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
			if(action.equals("delete")){
				System.out.println(action);
				if(delToCart(request, response)){
					
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}
				else{
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}
			}
			
		}	
	}
    private boolean addToCart(HttpServletRequest request, HttpServletResponse response){
    	
    	String id = request.getParameter("id");
    	String number =request.getParameter("num");
    	Items item=idao.getItemsById(Integer.parseInt(id));
    	//判断是否第一次添加进购物车
    	if(request.getSession().getAttribute("cart") ==null){
    		Cart cart =new Cart();
    		request.getSession().setAttribute("cart", cart);   		
    	}
    	Cart cart= (Cart) request.getSession().getAttribute("cart");
    	if(cart.addGoods(item, Integer.parseInt(number))){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    
    private boolean delToCart(HttpServletRequest request, HttpServletResponse response){
    	
    	String id = request.getParameter("id");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
	    Items item = idao.getItemsById(Integer.parseInt(id));
    	System.out.println(item);
    	if(cart.removeGoods(item)){
    		return true;
    	}else{
    		return false;
    	}
    }
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
