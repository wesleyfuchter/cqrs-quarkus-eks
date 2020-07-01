import http from 'k6/http';
import {check, sleep} from 'k6';

export default function() {
  const res = http.get('http://bankaccount.ga/balance-service/balances?accountId=wesley');
  check(res, { 'success getting balance': (r) => r.status === 200 });
  sleep(1);
}