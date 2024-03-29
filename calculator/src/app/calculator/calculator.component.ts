import { Component } from '@angular/core';
import { CalculatorService } from '../service/calculator.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-calculator',
  standalone: true,
  imports: [],
  templateUrl: './calculator.component.html',
  styleUrl: './calculator.component.scss'
})
export class CalculatorComponent {

  result : Number | undefined;

  checkoutForm = this.formBuilder.group({
    name: '',
    address: ''
  });

  constructor(
    private calculatorService: CalculatorService,
    private formBuilder: FormBuilder,
  ) {}

  onSubmit(): void {
    // Process checkout data here
    this.calculate();
    console.warn('Your order has been submitted', this.checkoutForm.value);
    this.checkoutForm.reset();
  }

  calculate(op){
    
  const request = {
    letf:'',
    right:''
  }

  this.calculatorService.post(request, op)
    .subscribe(
      (res: any) => {
       this.result = res;
      },
      (err: any) => {
        console.log('error', err);
      }
    );
  }
}
