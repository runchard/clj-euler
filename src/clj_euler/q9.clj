(ns clj-euler.q9)



(defn pythagorean
  [n]
  (->> (for [a (range 1 (quot 1000 3))
             b (range a (quot (- 1000 a) 2))]
         [a b (- 1000 a b)])
       (filter (fn [[a b c]]
                 (= (+ (square a)
                       (square b))
                    (square c))))
       (first)))

(assert (= (pythagorean 1000) [200 375 425] ))
(assert (= (reduce * (pythagorean 1000)) 31875000))
