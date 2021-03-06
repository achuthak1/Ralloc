<%-- 
    Document   : addSubject
    Created on : Mar 31, 2017, 9:05:41 PM
    Author     : Mahesh
--%>
<%@page import="com.ralloc.model.Department"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.ralloc.model.Subject"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ralloc.bean.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Subject | Database of Subjects</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/materialize.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
        <script src="./js/jquery.js"></script>
        <script type="text/javascript">
            function verifyCourseCode(){
                var codePatt = /\d\d\S\S\d\S\S\S\S\S/;
                if(!(codePatt.test(document.getElementById("coursecode").value)))
                {
                    alert("Please enter a valid Course Code in the format NNTTNTTTTT");
                }
            }
        </script>
    </head>
    <body>
        <nav>
            <div class="nav-wrapper custom-nav">
                <a href="${pageContext.request.contextPath}/home" class="brand-logo"><img src="${pageContext.request.contextPath}/images/BMSCE_Logo.svg" class="clg-logo"></a>
                
                <ul id="nav-mobile" class="left clg-name">
                    <li><a href="${pageContext.request.contextPath}/home"><h5>B.M.S COLLEGE OF ENGINEERING, BANGALORE - 560 019</h5><h6>(Autonomous Institute, Affiliated to VTU)</h6></a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
                <h4>Enter the details of the subject to be added</h4>
                <form method="POST" action="${pageContext.request.contextPath}/Subject/add">

                    <div class="row">
                        <div class="col l6 m6 s12">
                            <h5>
                                Please enter the course code of the subject
                            </h5>
                            <br>
                        </div>
                        <div class="col l6 m6 s12">
                            <input type="text" name="courseCode" id="coursecode" required placeholder="Format: nnTTnTTTTT" onblur="verifyCourseCode()"/>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col l6 m6 s12">
                            <h5>
                                Please enter the Name of the subject
                            </h5>
                            <br>
                        </div>
                        <div class="col l6 m6 s12">
                            <input type="text" name="name" id="coursecode" required />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col l6 m6 s12">
                            <h5>
                                Institutional Elective 
                            </h5>
                            <br>
                        </div>
                        <div class="col l3 m3 s6">
                            <p>
                                <input name="instElectGrp" type="radio" id="instYes" value="yes"/>
                                <label for="instYes">Yes</label>
                            </p>
                        </div>
                        <div class="col l3 m3 s6">
                            <p>
                                <input name="instElectGrp" type="radio" id="instNo" checked value="no"/>
                                <label for="instNo">No</label>
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col l6 m6 s12">
                            <h5>
                                Cluster Elective 
                            </h5>
                            <br>
                        </div>
                        <div class="col l3 m3 s6">
                            <p>
                                <input name="clustElectGrp" type="radio" id="clustYes" value="yes"/>
                                <label for="clustYes">Yes</label>
                            </p>
                        </div>
                        <div class="col l3 m3 s6">
                            <p>
                                <input name="clustElectGrp" type="radio" id="clustNo" checked value="no"/>
                                <label for="clustNo">No</label>
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col l6 m6 s12">
                            <h5>
                                Department Elective 
                            </h5>
                            <br>
                        </div>
                        <div class="col l3 m3 s6">
                            <p>
                                <input name="deptElectGrp" type="radio" id="deptYes" value="yes"/>
                                <label for="deptYes">Yes</label>
                            </p>
                        </div>
                        <div class="col l3 m3 s6">
                            <p>
                                <input name="deptElectGrp" type="radio" id="deptNo" checked value="no"/>
                                <label for="deptNo">No</label>
                            </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col l6 m6 s12">
                            <h5>
                                Department
                            </h5>
                            <br>
                        </div>
                        <div class="col l4 m4 s12">
                            <select class="browser-default" name="department">
                              <option value="" disabled selected>Choose department</option>
                              <%
                                HashMap deptList = Department.getDepartments();
                                //Get the available rooms from the database here and put it to this arraylist
                                //The data will be in a hashmap with department name as value 
                                //The key of the hashmap will be given as value to the value field of option and value will be string of the option
                                for(Object o: deptList.keySet()){
                                    out.println("<option value="+ (Integer)o +">"+ deptList.get(o) +"</option>");
                                }
                              %>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col l6 m6 s12">
                            <h5>
                                Has Dependency ? 
                            </h5>
                            <br>
                        </div>
                        <div class="col l3 m3 s6 has-dependency">
                            <p>
                                <input name="hasDependency" type="radio" id="depYes" onclick="toggleDependency()" value="yes"/>
                                <label for="depYes">Yes</label>
                            </p>
                        </div>
                        <div class="col l3 m3 s6">
                            <p>
                                <input name="hasDependency" type="radio" id="depNo" onclick="toggleDependency()" checked value="no"/>
                                <label for="depNo">No</label>
                            </p>
                        </div>
                    </div>
<!--                    <div class="dependency-matrix container">
                        <h5>Please select the rooms on which it is dependent</h5>
                        <div class="row">
                        
                        </div>
                    </div>-->
                    <br>
                    <br>
                    <div class="row">
                        <div class="col l2 m2 s4 right">
                            <button class="btn waves-effect waves-light custom-btn" type="submit">Add Subject</button>
                        </div>
                    </div> 	
                    <br>
                
            </form>
            
        </div>
        <br>
        <br> 
        
        <%--
            The below form is to take the arraylist of objects to the servlet
        --%>
        <form action="${pageContext.request.contextPath}/Subject/confirm" method="POST">
            <div class="row">
                <div class="col l2 m2 s4 right">
                    <button class="btn waves-effect waves-light custom-btn">Continue</button>
                </div>
            </div>
        </form>
        
        
        <br>
        <br>
        <footer class="custom-footer">
            <div class="footer-copyright">
                <div class="container" style="color: white; margin-top: 15px;">
            © 2017 BMSCE
            <!--<a class="text-lighten-4 right" href="#!" style="color: white;">Developed by Department of ISE</a>-->
            </div>
          </div>
        </footer>
    </body>
</html>