(ns clj-euler.q3)

"The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?"

(defn min-factor
  [n]
  (loop [[f & fs] (range 2 (int (Math/sqrt n)))]
    (if (nil? f)
      n                                 ;n is prime
      (if (zero? (mod n f))
        f
        (recur fs)))))

(defn max-factor
  [n]
  (loop [n n]
    (let [min (min-factor n)]
      (if (= n min)
        n
        (recur (/ n min))))))

(assert (= (min-factor 13195) 5))
(assert (= (max-factor 13195) 29))

(assert (= (max-factor 600851475143) 6857))


