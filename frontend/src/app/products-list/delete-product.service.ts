import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DeleteProductService {

  constructor(private http: HttpClient) {}

  execute(productId: string) {
    return this.http.delete(`${environment.apiUrl}/products/${productId}`);
  }
}
