/* 이클립스에서 Alt+s를 누르면 현재 라인 별로 쿼리가 실행됨. 자세한 것은 우클릭. */

/* 멤버 확인  */
SELECT * FROM "USER"."MEMBER"

/* chap07.criteria.mkyong에서 연습한 코드 */
select * from "USER"."STOCKDAILYRECORD"  where volume >= 0
select * from "USER"."STOCKDAILYRECORD"  where date >= timestamp('2014-01-01 00:00:00')
