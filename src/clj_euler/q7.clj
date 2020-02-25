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


(defn incremental-sieve-prime-seq-basic
  ([]
   (incremental-sieve-prime-seq-basic {} 2))
  ([p-cache prime-candidate]
   (lazy-seq
    (if-let [base-primes (p-cache prime-candidate)]
      (incremental-sieve-prime-seq-basic
       (reduce #(update %
                        (+ %2 prime-candidate)
                        (fnil conj [])
                        %2)
               (dissoc p-cache prime-candidate)
               base-primes)
       (inc prime-candidate))
      (cons prime-candidate
            (incremental-sieve-prime-seq-basic
             (assoc p-cache
                    (+ prime-candidate prime-candidate)
                    [prime-candidate])
             (inc prime-candidate)))))))


(defn incremental-sieve-prime-seq
  ([]
   (concat [2 3] (incremental-sieve-prime-seq {9 [3]} 5)))
  ([p-cache prime-candidate]
   (lazy-seq
    (if-let [base-primes (p-cache prime-candidate)]
      (incremental-sieve-prime-seq
       (reduce #(update %
                        (+ %2 %2 prime-candidate)
                        (fnil conj [])
                        %2)
               (dissoc p-cache prime-candidate)
               base-primes)
       (+ prime-candidate 2))
      (cons prime-candidate
            (incremental-sieve-prime-seq
             (assoc p-cache
                    (* prime-candidate 3)
                    [prime-candidate])
             (+ prime-candidate 2)))))))

;;; copied from `clojure.contrib.lazy-seqs/primes`
(def primes
  (concat
   [2 3 5 7]
   (lazy-seq
    (let [primes-from
          (fn primes-from [n [f & r]]
            (if (some #(zero? (rem n %))
                      (take-while #(<= (* % %) n) primes))
              (recur (+ n f) r)
              (lazy-seq (cons n (primes-from (+ n f) r)))))
          wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6  4  2
                        6 4 6 8 4 2 4 2 4 8 6 4 6 2  4  6
                        2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
      (primes-from 11 wheel)))))


(assert (= (last (last (take 1 (drop 4 (iterate prime-seq [2 3])))))
           13))
(assert (= (last (last (take 1 (drop 9999 (iterate prime-seq [2 3])))))
           104743))

(assert (= (first (drop 10000 (incremental-sieve-prime-seq-basic)))
           104743))

(assert (= (first (drop 10000 (incremental-sieve-prime-seq)))
           104743))

(assert (= (first (drop 10000 primes)) 104743))
