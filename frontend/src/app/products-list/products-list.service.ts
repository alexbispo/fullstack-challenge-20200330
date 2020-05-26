import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment'
import { Observable } from 'rxjs';
import { Product } from '../product/product';

@Injectable({
  providedIn: 'root'
})
export class ProductsListService {

  constructor(private http: HttpClient) { }

  execute() : Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.apiUrl}/products`);
  }
}
