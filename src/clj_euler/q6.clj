(ns clj-euler.q6)

(defn sum-square-diff
  [n]
  (- (#(* % % ) (reduce + (range 1 (inc n))))
     (reduce + (map #(* % %) (range 1 (inc n))))))

(assert (= (sum-square-diff 10) 2640))
(assert (= (sum-square-diff 100) 25164150))
