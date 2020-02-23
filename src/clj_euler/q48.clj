(ns clj-euler.q48)

"
The series, 1^1 + 2^2 + 3^3 + ... + 1010 = 10405071317.
Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
"

(defn power
  [n]
  (reduce * (repeat n n)))

(defn power-mod
  [n]
  (reduce #(mod (* % %2) 10000000000) (repeat n n)))

(defn self-powers
  [n]
  (reduce + (map (comp power-mod bigint) (range 1 (inc n)))))


(assert (= (self-powers 10)   405071317))
(assert (= (mod (self-powers 1000) 10000000000) 9110846700N))

