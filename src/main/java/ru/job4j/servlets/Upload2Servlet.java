package ru.job4j.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 3, // 1 MB
        maxFileSize = 1024 * 1024 * 15,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Upload2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> images = new ArrayList<>();
        for (File name : new File("/Users/oryngalikarimzhan/IdeaProjects/job4j_hibernate/./src/main/webapp/images/").listFiles()) {
            images.add(name.getName());
        }
        req.setAttribute("images", images);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/upload.jsp");
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("lastname"));
//        search by post id
//        if it exists add to images names list new name of image and return list as a response
//        else create list and add images names to this list and return list as a response
        for (Part part : request.getParts()) {
            String contentType = part.getContentType();
            List<String> images = new ArrayList<>();
            if ("image/jpeg".equals(contentType) || "image/png".equals(contentType)) {
                part.write("/Users/oryngalikarimzhan/IdeaProjects/job4j_hibernate/./src/main/webapp/images/" + part.getSubmittedFileName());
                images.add(part.getSubmittedFileName());

            }
        }
        response.getWriter().print("The file uploaded successfully.");
    }
}
