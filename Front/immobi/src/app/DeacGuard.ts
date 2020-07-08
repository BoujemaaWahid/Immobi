//tslint:disable
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
export interface Deactivator {
    confirm(): Promise<boolean>
}

export class DeacGuard implements CanDeactivate<Deactivator>{
    canDeactivate(component: Deactivator,
        currentRoute: ActivatedRouteSnapshot,
        currentState: RouterStateSnapshot,
        nextState?: RouterStateSnapshot): boolean | import("@angular/router").UrlTree | import("rxjs").Observable<boolean | import("@angular/router").UrlTree> | Promise<boolean | import("@angular/router").UrlTree> {
        return component.confirm();
    }
}