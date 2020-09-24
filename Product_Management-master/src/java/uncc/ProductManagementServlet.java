/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uncc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import murach.business.Product;
import murach.data.ProductTable;
//import murach.data.UserIO;

/**
 *
 * @author Bryson
 */
public class ProductManagementServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
                HttpSession session = request.getSession();
        
        // get current action
        /*
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        */
        
        String action = request.getParameter("action");
        List<Product> products = ProductTable.selectProducts();
        session.setAttribute("products", products);
        // perform action and set URL to appropriate page
        String url = "/index.jsp";
        
        if(session.getAttribute("user") == null){
            url = "/login.jsp"; 
        } else if (action.equals("displayProducts")) {
            url = "/products.jsp";    // the "products" page
            session.removeAttribute("editproduct");
//String path2;
            //path2 = getServletContext().getRealPath("/WEB-INF/products.txt");
            //List<Product> products = ProductTable.selectProducts(path2);
            session.setAttribute("products", products);
        } else if(action.equals("addProduct")) {
            url = "/product.jsp";
            
            if(request.getParameter("productCode") != null) {
                Product editproduct = ProductTable.selectProduct(request.getParameter("productCode"));
                session.setAttribute("editproduct", editproduct);

            } else {
                
                String code = request.getParameter("code");
                if(code != null) {
                    String description = request.getParameter("description");
                    try {
                        double price = Double.parseDouble(request.getParameter("price"));
                       
                        if(price < 0) {
                        
                        } else {
                            Product product = new Product();

                            product.setCode(code);
                            product.setDescription(description);
                            product.setPrice(price);

                            if(session.getAttribute("editproduct") == null) {
                                ProductTable.insertProduct(product);
                                Product editproduct = ProductTable.selectProduct(request.getParameter("code"));
                                session.setAttribute("editproduct", editproduct);
                                
                            } else {
                                ProductTable.updateProduct(product);
                                Product editproduct = ProductTable.selectProduct(request.getParameter("code"));
                                session.setAttribute("editproduct", editproduct);
                            }
                        }
                    } catch(NumberFormatException e) {
                        
                    }
                }
            }

        } else if(action.equals("displayProduct")) {
            url = "/product.jsp";
        } 
        else if (action.equals("deleteProduct")) {   
            
            
            String code = request.getParameter("productCode");
            String test = "";
            Product delete = ProductTable.selectProduct(code);
            
            if(request.getParameterMap().containsKey("delete1")){
                test = request.getParameter("delete1");
            }
            
            
            if(test.equals("Yes")){
                ProductTable.deleteProduct(delete);
                url = "/productManagement?action=displayProducts";
            } else{
                url = "/confirmDelete.jsp";
                session.setAttribute("delete", delete);
            }

            
            
            
            
            // get parameters from the request
            /*
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // store data in User object and save User object in database
            User user = new User(firstName, lastName, email);
            UserDB.insert(user);
            
            
            // set User object in request object and set URL
            session.setAttribute("user", user);
            */
        }
        /*
        try (PrintWriter out = response.getWriter()) {
            TODO output your page here. You may use following sample code.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductManagementServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManagementServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        */
        // forward request and response objects to specified URL
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
