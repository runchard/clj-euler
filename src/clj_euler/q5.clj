(ns clj-euler.q5)


"2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?"


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


;;; find all needed prime count and multiply them all
(defn merge-max-primes
  [n]
  (->> (range 1 n)
       (map count-prime )
       (apply merge-with #(max %1 %2))))

(defn calc-minimal-composite
  [n]
  (int (reduce #(* %1 (Math/pow (first %2) (second %2))) 1 (merge-max-primes n))))

(assert (= (calc-minimal-composite 10) 2520))
(assert (= (calc-minimal-composite 20) 232792560))
