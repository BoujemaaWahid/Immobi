
//tslint:disable
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
export class Guard implements CanActivate {

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | import("@angular/router").UrlTree | import("rxjs").Observable<boolean | import("@angular/router").UrlTree> | Promise<boolean | import("@angular/router").UrlTree> {
        return !localStorage.getItem("idUser")
    }
    
}

export class AdminGuard implements CanActivate {

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | import("@angular/router").UrlTree | import("rxjs").Observable<boolean | import("@angular/router").UrlTree> | Promise<boolean | import("@angular/router").UrlTree> {
        if( !localStorage.getItem("idUser") )return false
        if( !localStorage.getItem("user_type") ) return false;
        else if( localStorage.getItem("user_type") != "1" )return false;
        return true;
    }
    
}