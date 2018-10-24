/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pedago
 */
@WebServlet(urlPatterns = {"/step2"})
public class step2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private DAOextends myDAO; // L'objet à tester
    private DataSource myDataSource; // La source de données à utiliser
	
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        myDataSource = DataSourceFactory.getDataSource();
	myDAO = new DAOextends(myDataSource);
        List<String> states= new ArrayList<>();
        try {
            states = myDAO.listOfState();
        } catch (DAOException ex) {
            Logger.getLogger(step2.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet step2</title>");  
            out.println("<style>\n" +
"table, th, td {\n" +
"    border: 1px solid black;\n" +
"}\n" +
"</style>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form>");
            

            out.println("<select id=\"etat\" name=\"etat\">");
            for(String state : states){
                out.println("<option value="+ state +">" + state + "</option>");
            }
            out.println("<input type=\"submit\" name=\"validation\" value=\"VALIDER\" />");
            out.println("</select>");
            out.println("</form>");
            out.println("<table style=\"width:100%\">\n  <tr><th>ID</th><th>NOM</th><th>ADDRESS</th></tr>");
            
            String buttonClicked = request.getParameter("validation");
            if(buttonClicked != null){
                String s = request.getParameter("etat");
                List<CustomerEntity> customers = myDAO.customersInState(s);
                for(CustomerEntity customer : customers){
                    out.println("<tr><td>"+ customer.getCustomerId() +"</td><td>"+ customer.getName()+"</td><td>"+ customer.getAddressLine1() +"</td></tr>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (DAOException ex) {
            Logger.getLogger(step2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
