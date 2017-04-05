<%-- 
    Document   : downloadAllotment
    Created on : Apr 5, 2017, 8:19:30 PM
    Author     : Mahesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Download Arrangement</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
    </head>
    <body>
        <nav>
            <div class="nav-wrapper custom-nav">
                <a href="#" class="brand-logo"><img src="${pageContext.request.contextPath}/images/BMSCE_Logo.svg" class="clg-logo"></a>
                
                <ul id="nav-mobile" class="left clg-name">
                    <li><a href="#"><h5>B.M.S COLLEGE OF ENGINEERING, BANGALORE - 560 019</h5><h6>(Autonomous Institute, Affiliated to VTU)</h6></a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <h3 class="center">Download Seating Arrangement</h3>
            <hr width="100%" style="border-top:1px solid #989896">
            <div class="row">
                <div class="col l4 m4 s12" style="margin-top: 30px;">
                    <a href="${pageContext.request.contextPath}/#" target="_blank">
                        <div class = "card-panel custom-btn center" style="height: 150px;">
                            <div class="card-content admin-card-content">
                            <span class="flow-text" style="color: white;">DOWNLOAD ALLOTMENT</span>
                        </div>
                    </div>
                    </a>
                </div>
                <div class="col l4 m4 s12" style="margin-top: 30px;">
                    <a href="${pageContext.request.contextPath}/#" target="_blank">
                        <div class = "card-panel custom-btn center" style="height: 150px;">
                            <div class="card-content admin-card-content">
                            <span class="flow-text" style="color: white;">DOWNLOAD B-FORM</span>
                        </div>
                    </div>
                    </a>
                </div>
                <div class="col l4 m4 s12" style="margin-top: 30px;">
                    <a href="${pageContext.request.contextPath}/#" target="_blank">
                        <div class = "card-panel custom-btn center" style="height: 150px;">
                            <div class="card-content admin-card-content">
                            <span class="flow-text" style="color: white;">DOWNLOAD INDENT</span>
                        </div>
                    </div>
                    </a>
                </div>
            </div>
        </div>
        <footer class="custom-footer" style="bottom: 0px; position: fixed;">
            <div class="footer-copyright">
                <div class="container" style="color: white; margin-top: 15px;">
            © 2017 BMSCE
            <a class="text-lighten-4 right" href="#!" style="color: white;">Developed by Department of ISE</a>
            </div>
          </div>
        </footer>
    </body>
</html>