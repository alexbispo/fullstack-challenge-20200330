import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Product } from '../product/product';
import { ProductEdit } from './product-edit';
import { FindOneProductService } from './find-one-product.service';
import { ProductEditService } from './product-edit.service';

@Component({
  templateUrl: './product-edit.component.html'
})
export class ProductEditComponent implements OnInit {

  productForm: FormGroup;
  productId: string;
  product$: Observable<Product>;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private findOneProductService: FindOneProductService,
    private productEditService: ProductEditService
  ) {}

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      title: ['', [Validators.required, Validators.maxLength(100)]],
      type: ['', [Validators.required], Validators.maxLength(100)],
      price: ['', Validators.required]
    });

    this.productId = this.route.snapshot.params.productId;
    this.product$ = this.findOneProductService.execute(this.productId);
    this.product$.subscribe(
        (product) => {
          this.productForm = this.formBuilder.group({
            title: [product.title, Validators.required],
            type: [product.type, Validators.required],
            price: [product.price, Validators.required]
          });
        },
        err => {
          console.log(err);
          alert('Produto não encontrado.')
          this.router.navigate(['']);
        }
      );
  }

  submit() {
    if (this.productForm.valid && !this.productForm.pending) {
      const productToUpdate = this.productForm.getRawValue() as ProductEdit;
      this.productEditService.execute(this.productId, productToUpdate)
        .subscribe(
          () => this.router.navigate(['']),
          err => {
            console.error(err);
            alert('Erro ao tentar enviar os dados para o servidor. Por favor tentar novamente.');
          }
        );
    }
  }
}
