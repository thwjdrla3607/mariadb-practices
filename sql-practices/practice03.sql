-- 조인(JOIN) SQL 문제입니다.

-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select a.emp_no as 사번, a.first_name as 이름, b.salary as 연봉
from employees a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
order by salary desc;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, b.title as 직책
from employees a, titles b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
order by concat(a.first_name, ' ', a.last_name);

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, c.dept_name as 부서
from employees a, dept_emp b, departments c
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and b.to_date = '9999-01-01'
order by concat(a.first_name, ' ', a.last_name) asc;

-- 문제4.
-- 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select a.emp_no as 사번, concat(a.first_name, ' ', a.last_name) as 이름, b.salary as 연봉, c.title as 직책 , d.dept_name as 부서
from employees a, salaries b, titles c, departments d, dept_emp e
where a.emp_no = b.emp_no 
and a.emp_no = c.emp_no 
and  a.emp_no = e.emp_no
and d.dept_no = e.dept_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and e.to_date = '9999-01-01'
order by concat(a.first_name, ' ', a.last_name) asc;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요.
-- 현재 ‘Technique Leader’의 직책으로 근무하는 사원은 고려하지 않습니다.
select a.emp_no as 사번, a.first_name as 이름
from employees a, titles b
where a.emp_no = b.emp_no
and title = 'Technique Leader';

-- 문제6.
-- 직원 이름(last_name) 중에서 s로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select a.last_name as 이름, b.dept_name as 부서명, d.title as 직책
from employees a, departments b, dept_emp c, titles d
where a.emp_no = c.emp_no 
and a.emp_no =  d.emp_no
and b.dept_no = c.dept_no
and a.last_name like 's%';

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가
-- 이름, 급여 출력
-- 급여가 큰 순서대로 출력하세요.
select a.first_name as 이름, b.salary as 급여
from employees a, salaries b, titles c
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and c.title = 'Engineer'
and b.salary >= 4000
order by b.salary desc;

-- 문제8.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select a.dept_name as 부서명, avg(c.salary) as 평균연봉
from departments a, dept_emp b, salaries c 
where a.dept_no = b.dept_no
and b.emp_no = c.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
group by a.dept_name
order by avg(salary) desc;

-- 문제9.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select a.title as 직책, avg(b.salary) as 평균연봉
from titles a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.title
order by avg(salary) desc;