package com.ttn.linksharing

//If session.user is not set then redirect user to login index,
// this should be done in interceptor
// - user index action should render session user username
class LoginCheckInterceptor {

    LoginCheckInterceptor(){
        matchAll().excludes(controller: 'login', action: 'loginHandler')
    }

    boolean before() {
        if (!session.user) {
            flash.error= "NO ACTIVE SESSION"
           // redirect(action:"login/index")
        }

        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
