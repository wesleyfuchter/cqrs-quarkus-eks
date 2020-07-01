import http from 'k6/http';
import {check, sleep} from 'k6';

const incomePayload = JSON.parse(open("../income-transaction.json"));
const expensePayload = JSON.parse(open("../expense-transaction.json"));

export default function() {

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  check(http.post('http://bankaccount.ga/transaction-service/transactions', JSON.stringify(incomePayload), params), { 'success creating income': (r) => r.status === 201 });
  sleep(1);

  check(http.post('http://bankaccount.ga/transaction-service/transactions', JSON.stringify(expensePayload), params), { 'success creating expense': (r) => r.status === 201 });
  sleep(1);

  check(http.get('http://bankaccount.ga/balance-service/balances?accountId=wesley'), { 'success getting balance': (r) => r.status === 200 });
  sleep(1);

}