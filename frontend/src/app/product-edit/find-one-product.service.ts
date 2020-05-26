import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Product } from '../product/product';

@Injectable({
  providedIn: 'root'
})
export class FindOneProductService {

  constructor(private http: HttpClient) {}

  execute(productId: string) {
    return this.http.get<Product>(`${environment.apiUrl}/products/${productId}`);
  }
}
