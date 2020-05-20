//package servlets;
//
//import dao.services.LoginDao;
//import entity.Customer;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//
//
//public class LoginServlet extends HttpServlet {
//   private static final long serialVersionUID = 1L;
//
//    private LoginDao loginDao= new LoginDao();
//
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
//        dispatcher.forward(request, response);
//        }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        Customer customer = new Customer();
//        customer.setEmail(email);
//        customer.setPassword(password);
//
//
//        try {
//            if(loginDao.validate(customer)) {
//
//                RequestDispatcher dispatcher  = request.getRequestDispatcher("/WEB-INF/view/loginsuccess.jsp");
//                dispatcher.forward(request, response);
//
//            } else {
//
//                RequestDispatcher dispatcher  = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
//                dispatcher.forward(request, response);
//
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
//
