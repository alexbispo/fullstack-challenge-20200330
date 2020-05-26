import { Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Product } from '../product/product';
import { ProductsListService } from '../products-list/products-list.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductsResolver implements Resolve<Observable<Product[]>> {

  constructor(private productsListService: ProductsListService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product[]> {
    const emptyProducts: Product[] = [];
    return this.productsListService.execute()
      .pipe(map(res => res),
            catchError(error => {
              return of(emptyProducts);
            })
      );
  }

}
