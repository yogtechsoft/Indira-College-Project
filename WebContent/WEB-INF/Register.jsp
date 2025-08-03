<html lang = "en">  
   <head>  
      <meta charset = "utf-8">  
      <meta name = "viewport" content = "width = device-width, initial-scale = 1, shrink-to-fit = no">  
      <link rel = "stylesheet"   
         href = "https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"  
         integrity = "sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"   
         crossorigin = "anonymous">         
      <title> Bootstrap 4 Registration Form Example </title>  
   <style>  
 .note  
{  
    text-align: center;  
    height: 80px;  
    background: -webkit-linear-gradient(left, #0072ff, #8811c5);  
    color: #fff;  
    font-weight: bold;  
    line-height: 80px;  
}  
body {  
  margin: 0;  
  font-family: 'Lato', sans-serif;  
  font-size: 12px;  
  line-height: 1.8em;  
  text-transform: none;  
  letter-spacing: .075em;  
  font-weight: bold;  
  font-style: normal;  
  text-decoration: none;  
  color: #e7bd74;  
  background-color: rgba(34,28,28);  
  display: block;  
}  
.title {  
  margin-top: 2rem;  
  margin-bottom: 1rem;  
}  
.form-content  
{  
    padding: 5%;  
    border: 1px solid #ced4da;  
    margin-bottom: 2%;  
}  
.form-control {  
    border-radius: 1.5rem;  
}  
.btnSubmit  
{  
    border: none;  
    border-radius: 1.5rem;  
    padding: 1%;  
    width: 20%;  
    cursor: pointer;  
    background: #0062cc;  
    color: #fff;  
}  
h1 {  
    font-family: sans-serif;  
  display: block;  
  font-size: 1rem;  
  font-weight: bold;  
  text-align: center;  
  letter-spacing: 3px;  
  color: hotpink;  
 text-transform: uppercase;  
padding-top: 20px;  
}  
a {  
  text-decoration: none;  
  color: #fff;  
}  
a:hover {  
  text-decoration: none;  
  color: #fff;  
}  
  
      </style>  
   </head>  
   <body>  
<div class="container register-form">  
            <div class="form">  
                <div class="note">  
                    <h1> Bootstrap 4 Registration Form Example. </h1>  
                </div>  
                <div class="form-content">  
                    <div class="row">  
                        <div class="col-md-6">  
                            <div class="form-group">  
                                <input type="text" class="form-control" placeholder="Your Name *" value=""/>  
                            </div>  
                            <div class="form-group">  
                                <input type="text" class="form-control" placeholder="Phone Number *" value=""/>  
                            </div>  
                        </div>              
                        <div class="col-md-6">  
                            <div class="form-group">  
                                <input type="text" class="form-control" placeholder="Your Password *" value=""/>  
                            </div>  
                            <div class="form-group">  
                                <input type="text" class="form-control" placeholder="Confirm Password *" value=""/>  
                            </div>  
                        </div>  
                    </div>  
    <div class="row align-items-center mt-4">  
            <div class="col">  
              <input type="text" class="form-control" placeholder=" Enter Email Address">  
            </div>  
          </div> <div class="row align-items-center mt-4">  
            <div class="col">  
              <input type="text" class="form-control" placeholder=" Address">  
            </div>  
          </div>  
    <div class="row justify-content-start mt-4">  
            <div class="col">  
              <div class="form-check">  
                <label class="form-check-label">  
                  <input type="checkbox" class="form-check-input">  
                  I hereby agree to the <a href="/"> Terms and Conditions. </a>  
                </label>  
              </div>  
                    <button type="button" class="btnSubmit"> Submit </button>  
                </div>  
            </div>  
        </div>  
</body>  
</html>  