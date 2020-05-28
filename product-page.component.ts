import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from '../../Model/Product.model';
import { Coupon } from '../../Model/Coupon.model';
import { CapstoreService } from '../../service/capstore.service';

@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.css'],
})
export class ProductPageComponent implements OnInit {
  category: string = 'All Products';
  filter: string = '';
  searchProduct: string = '';
  categoryProducts: Product[];
  allProducts: Product[];
  products: Product[];
  singleProduct: Product;
  couponList: Coupon[];
  columnDisplay: number = 3;
  noShow: boolean = false;
  discountPercent: string = '';

  constructor(
    private _capstoreService: CapstoreService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this._capstoreService.getAllProducts().subscribe((response) => {
      this.allProducts = response;

      this.products = this.removeDuplicates(
        this.allProducts,
        'productCategory'
      );
      console.log(this.products);
    });

    let id = parseInt(this.route.snapshot.paramMap.get('id'));
    this._capstoreService
      .getProduct(id)
      .subscribe((response) => (this.singleProduct = response));
  }

  removeDuplicates(array, key) {
    let lookup = new Set();
    return array.filter((obj) => !lookup.has(obj[key]) && lookup.add(obj[key]));
  }

  search() {
    this._capstoreService
      .getSearchProducts(this.searchProduct)
      .subscribe((response) => {
        this.categoryProducts = response;
      });
  }

  show() {
    alert('1');
  }

  closePop() {
    document.getElementById('id01').style.display = 'none';
  }
  openPop() {
    document.getElementById('id01').style.display = 'block';
  }

  openNav() {
    document.getElementById('mySidebar').style.width = '250px';
    document.getElementById('main').style.marginLeft = '250px';
  }

  closeNav() {
    document.getElementById('mySidebar').style.width = '0';
    document.getElementById('main').style.marginLeft = '0';
  }
  homePage() {
    this.router.navigate(['homepage']);
  }
}