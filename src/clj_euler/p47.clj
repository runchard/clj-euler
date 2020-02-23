(ns clj-euler.p47)

(defn min-factor
  [n]
  (loop [[f & fs] (range 2 (inc (int (Math/sqrt n))))]
    (if (nil? f)
      n                              ;n is prime
      (if (zero? (mod n f))
        f
        (recur fs)))))


(defn count-prime
  [n]
  (loop [n      n
         result {}]
    (let [min (min-factor n)]
      (if (= n 1)
        result
        (recur (/ n min)
               (update result min (fnil inc 0)))))))

(defn consecutive
  [n]
  (loop [i  1
         cn 0]
    (if (= cn n)
      (- i n)
      (let [ps (count (count-prime i))]
        (if (= ps n)
          (recur (inc i) (inc cn))
          (recur (inc i) 0))))))

(assert (= (consecutive 2) 14))
(assert (= (consecutive 3) 644))
(assert (= (consecutive 4) 134043))

