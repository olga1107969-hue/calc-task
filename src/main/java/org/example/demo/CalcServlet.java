package org.example.demo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String num1Str = req.getParameter("num1");
        String num2Str = req.getParameter("num2");
        String op = req.getParameter("op");

        try {
            double n1 = Double.parseDouble(num1Str);
            double n2 = Double.parseDouble(num2Str);
            double result;

            switch (op) {
                case "+": result = n1 + n2; break;
                case "-": result = n1 - n2; break;
                case "*": result = n1 * n2; break;
                case "/":
                    if (n2 == 0) {
                        out.println("<h1>Ошибка: Деление на ноль!</h1>");
                        out.println("<a href='calc.html'>Назад</a>");
                        return;
                    }
                    result = n1 / n2;
                    break;
                default:
                    out.println("<h1>Ошибка: неизвестная операция!</h1>");
                    out.println("<a href='calc.html'>Назад</a>");
                    return;
            }

            out.println("<h1>Результат: " + n1 + " " + op + " " + n2 + " = " + result + "</h1>");
            out.println("<a href='calc.html'>Назад</a>");

        } catch (NumberFormatException e) {
            out.println("<h1>Ошибка: Введите корректные числа!</h1>");
            out.println("<a href='calc.html'>Назад</a>");
        }
    }
}

