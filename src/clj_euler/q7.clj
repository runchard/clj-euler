(ns clj-euler.q7)

(defn prime?
  [n possible-primes]
  (->> possible-primes
       (filter #(<= % (Math/sqrt n)))
       (every? #(not (zero? (mod n %))))))

(defn prime-seq
  [primes]
  (loop [result (last primes)]
    (let [new (+ result 2)]
      (if (prime? new primes)
        (conj primes new)
        (recur new)))))

(assert (= (last (last (take 1 (drop 4 (iterate prime-seq [2 3])))))
           13))
(assert (= (last (last (take 1 (drop 9999 (iterate prime-seq [2 3])))))
           104743))
