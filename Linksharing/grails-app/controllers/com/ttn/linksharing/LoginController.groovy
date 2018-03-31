package com.ttn.linksharing

import com.ttn.constants.DefaultPassword

/*Errors with proper message properties should be
rendered if user is not set and flash message should be set.
*/

class LoginController {

    def index() {


      }

    def logout() {
        session.invalidate()
        redirect(action:'login/index')
    }

    def loginHandler(String userName, String password) {
        println(userName)
        User user = User.findByUserNameAndPassword(userName, password)
        if(user!=null) {

            if(user.active) {
             session.user=user
                forward(controller: 'user', action: 'index')

            }
            else {
               flash.error = "Your account is not active"

            }
        }
        else
        {
            flash.error="User not found"
        }
        render(view:'index')


    }

    def register(){
        User admin = new User(email: "admin@gmail.com", password: DefaultPassword.PASSWORD, firstName: "admin", lastName: "portal", userName: 'adminPortal', photo: 121, admin: true, active: true)
        if(admin.save()){
            flash.message="Admin Saved Successfully"
        }
        else {
            flash.error="error"
        }

        //normal
        User normal = new User(email: "prachijulka@gmail.com", password: DefaultPassword.PASSWORD, firstName: "Prachi", lastName: "Julka", userName: 'PrachiJulka', photo: 122, admin: false, active: true)
        if(normal.save()){
            flash.message="Normal User Saved Successfully"

        }
        else {
            flash.error="error"

        }

        redirect(action: "index")
    }

}
