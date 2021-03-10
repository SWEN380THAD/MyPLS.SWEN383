<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Spring Boot Form Submission</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
          <!-- Font Awesome Icons -->
          <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
          <!-- Theme style -->
          <link rel="stylesheet" href="/css/adminlte.min.css">
    </head>
    <body>
      <div style="margin:auto;width:50%">
              <h1>Welcome to MyPLS</h1>



                   <div class="card card-info">
                               <div class="card-header">
                                 <h3 class="card-title"><h3><#if msg?? ><span style="color:red">${msg}</span><#else>Register Account</#if></h3>
                                  </div>
                               <!-- /.card-header -->
                               <!-- form start -->
                               <form class="form-horizontal" action="/form" method="post">
                                 <div class="card-body">
                                   <div class="form-group row">
                                     <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
                                     <div class="col-sm-10">
                                       <input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="email" <#if user?? >value =${user.email}</#if>>
                                     </div>
                                   </div>
                                   <div class="form-group row">
                                     <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
                                     <div class="col-sm-10">
                                       <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name ="pw">
                                     </div>
                                   </div>
                                   <div class="form-group row">
                                     <label for="inputPassword3" class="col-sm-2 col-form-label">Confirm Password</label>
                                       <div class="col-sm-10">
                                         <input type="password" class="form-control" id="inputPassword3" placeholder="Confirm Password" name ="pw2">
                                       </div>
                                    </div>
                                    <div class="form-group row">
                                      <label class="col-sm-2 col-form-label">Account Type</label>
                                       <div class="col-sm-10">
                                        <select class="form-control" name="type">
                                              <option>Learner</option>
                                              <option>Admin</option>
                                              <option>Professor</option>

                                        </select>
                                        </div>
                                    </div>

                                 </div>
                                 <!-- /.card-body -->
                                 <div class="card-footer">
                                   <button type="submit" class="btn btn-info">Create</button>
                                   <button type="reset" class="btn btn-default float-right">Cancel</button>
                                 </div>
                                 <!-- /.card-footer -->
                               </form>
                             </div>
                             <!-- /.card -->
                   <p>Already Have an Account?....<a href="/index">Log In</a></p>

        </div>
        <script src="/js/main.js"></script>
    </body>
</html>
