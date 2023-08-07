package com.lojavirtual.utils;

import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;

public class MailHtml {
    public String formatEmail(String nome, String username, String password) {

        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">").append("\n")
                .append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n").append("\n")
                .append("  <body style=\"background-color: #ffffff; font-size: 16px;\">").append("\n")
                .append("    <center>").append("\n")
                .append("      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"height:100%; width:600px;\">").append("\n")
                ///**          BEGIN EMAIL        **///
                .append("          <tr>").append("\n")
                .append("            <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding:30px\">").append("\n")
                .append("               <p style=\"text-align:left\">Olá, ").append("<b>").append(nome).append("</b>").append("<br><br> Segue abaixo seus dados de acesso para a Loja Virtual:").append("\n")
                .append("              </p>").append("\n")
                .append("              <p style=\"text-align:left\">").append("\n")
                .append("                <strong>Username: </strong>").append(username).append("\n").append("</p>").append("\n")
                .append("              <p style=\"text-align:left\"><strong>Senha padrão: </strong>").append(password)
                .append("              </p>").append("\n")
                .append("              <p style=\"text-align:left\">Por favor não esqueça de trocar a sua senha e aproveite os melhores preços em nossa loja.</p>").append("\n")
                .append("              <p style=\"text-align:left\">").append("\n")
                .append("              Obrigado,<br>Equipe Loja Virtual").append("\n")
                .append("              </p>").append("\n")
                .append("            </td>").append("\n")
                .append("          </tr>").append("\n")
                .append("        </tbody>").append("\n")
                .append("      </table>").append("\n")
                .append("    </center>").append("\n")
                .append("  </body>").append("\n")
                .append("</html>");
        return builder.toString();
    }

    public String formatSenhaVencidaHtml(String nomeUsuario) {
        String path = "src/main/resources/file/email.html";
        String content = null;
        try {
            content = Jsoup.parse(new File(path), "UTF-8").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.replaceAll("\\{nome\\}", nomeUsuario);
    }
}
