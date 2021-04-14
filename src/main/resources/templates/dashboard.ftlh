<!DOCTYPE html>
<!--
This is a the main Dashboard
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>myPLS</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Home</a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
              <a href="#" class="nav-link">Available Courses</a>
      </li>
       <li class="nav-item d-none d-sm-inline-block">
              <a href="/discussionDashboard/${user.email}" class="nav-link">Discussion Groups</a>
            </li>
      <li class="nav-item d-none d-sm-inline-block">
              <a href="/feedbackDashboard/${user.email}" class="nav-link">Feedback</a>
            </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Contact</a>
      </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">


      <li class="nav-item">
        <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
          <i class="fas fa-th-large"></i>
        </a>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index3.html" class="brand-link">

      <span class="brand-text font-weight-light">MyPLS</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">

        </div>
        <div class="info">
          <a href="#" class="d-block">${user.email}</a>
        </div>
      </div>
      <!-- Sidebar Menu -->

      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
                <#if user.type == "Admin">
          <li class="nav-item menu-open">
            <a href="/addCourseForm/${user.email}" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Add Course
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>

          </li>


          <li class="nav-item menu-open">
             <a href="/addDiscussionForm/${user.email}" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
               <p>
                 Add Group
                 <i class="right fas fa-angle-left"></i>
               </p>
             </a>

          </li>
           <#elseif user.type == "Professor">
          <li class="nav-item menu-open">
            <a href="/addQuizForm/${user.email}" class="nav-link active">
                <i class="nav-icon fas fa-tachometer-alt"></i>
                <p>
                  Create Quiz
                 <i class="right fas fa-angle-left"></i>
                </p>
              </a>

           </li>

</#if>
        </ul>
      </nav>

      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Courses</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
             <!-- <li class="breadcrumb-item active">Starter Page</li> -->
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-6">

          <#list courseList as courses>

             <div class="card card-primary card-outline">
               <div class="card-header">
                 <h5 class="m-0">${courses.name}</h5>
               </div>
               <div class="card-body">
                 <h4 class="card-title">-Prerequisites-</h4></br>
                  <p class="card-text">${courses.prerequisites}.</p>
                  <h4 class="card-title">-Requirements-</h4></br>
                  <p class="card-text">${courses.requirements}.</p>
                  <h4 class="card-title">-Description-</h4></br>
                  <p class="card-text">${courses.description}.</p>
                   <h4 class="card-title">-Professor-</h4></br>
                  <p class="card-text"><#if courses.professor??>${courses.professor_fName} ${courses.professor_lName}<#else>Not Assigned</#if></p>
                  <#if user.type == "Admin">
                     <a href="dashboard/deleteCourse/${courses.id}" class="btn btn-primary" data-toggle="modal" data-target="#modal-success">Delete Course</a>
                     <a href="/updateCourse/${user.email}/${courses.id}" class="btn btn-primary">Update Course</a>
                     <a href="dashboard/${user.email}/${courses.id}" class="btn btn-primary">View Course Lessons</a>
                  <#elseif user.type == "Professor">
                     <a href="dashboard/${user.email}/${courses.id}" class="btn btn-primary">Open Course</a>
                     <a href="dashboard/${user.email}/${courses.id}" class="btn btn-primary">Add Quiz</a>
                  <#else>
                  </#if>
                </div>
               </div>

          </#list>


        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

        <div class="modal modal-success fade" id="modal-success">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Success Modal</h4>
              </div>
              <div class="modal-body">
                <p>course has been deleted</p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>

              </div>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
    <div class="p-3">
      <h5>Title</h5>
      <p>Sidebar content</p>
    </div>
  </aside>
  <!-- /.control-sidebar -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      Anything you want
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/js/adminlte.min.js"></script>
</body>
</html>
