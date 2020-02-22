(ns clj-euler.q1)

"If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000."


(defn multiples-of
  [n]
  (iterate #(+ % n) n))

(defn multiples-of-3-or-5
  ([]
   (multiples-of-3-or-5 (multiples-of 3) (multiples-of 5)))
  ([[a & as] [b & bs]]
   (lazy-seq
    (apply cons 
           (cond (< a b)
                 [a (multiples-of-3-or-5 as (cons b bs))]
                 (= a b)
                 [a (multiples-of-3-or-5 as bs)]
                 :else
                 [b (multiples-of-3-or-5 (cons a as) bs)])))))

(defn sum-less-than
  [n]
  (reduce + (take-while #(< % n) (multiples-of-3-or-5))))


(assert (= (sum-less-than 10) 23))
(assert (= (sum-less-than 1000) 233168))


