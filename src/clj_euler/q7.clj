(ns clj-euler.q7
  (:require [clojure.data.priority-map :refer [priority-map]]))

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


(defn update-first-values
  [p-map v]
  ;; (println '[p-map v] [p-map v])

  (reduce #(update % (first %2) + (first %2))
          p-map
          (subseq p-map = v)))

(defn incremental-sieve-prime-seq
  ([]
   ;; TODO: can do some technique make prime seq faster
   (incremental-sieve-prime-seq (priority-map) 2))
  ([p-cache prime-candidate]
   (lazy-seq
    (let [[base-prime next-compose] (first p-cache)]
      (if (= next-compose prime-candidate)
        (incremental-sieve-prime-seq
         (update-first-values p-cache prime-candidate)
         (inc prime-candidate))
        (cons prime-candidate               ;prime case
              (incremental-sieve-prime-seq
               (assoc p-cache
                      prime-candidate
                      (+ prime-candidate
                         prime-candidate))
               (inc prime-candidate))))))))


(time (doall (take 1 (drop 10000 (incremental-sieve-prime-seq)))))

;; (assert (= (last (last (take 1 (drop 4 (iterate prime-seq [2 3])))))
;;            13))
;; (assert (= (last (last (take 1 (drop 9999 (iterate prime-seq [2 3])))))
;;            104743))
