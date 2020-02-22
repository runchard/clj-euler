(ns clj-euler.q2)

(def fibonacci
  (map first (iterate (fn [[a b]] [b (+' a b)]) [1 2])))

(assert (= (take 10 fibonacci)
           [1 2 3 5 8 13 21 34 55 89]))


(assert (= (->> fibonacci
                (filter #(even? %))
                (take-while #(< % 4000000))
                (reduce +))
           4613732))
