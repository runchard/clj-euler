(ns clj-euler.q4)

(defn num-to-list
  [n]
  (loop [n      n
         result []]
    (let [digit  (mod n 10)
          remain (quot n 10)]
      (if (= n 0)
        result
        (recur remain (conj result digit))))))

(defn palindromic?
  [n]
  (let [nlist  (num-to-list n)
        length (count nlist)]
    (every? #(= (nth nlist %) (nth nlist (- length % 1)))
            (range (quot length 2)))))

(assert (= (->> (for [x (range 900 999)
                      y (range 900 999)]
                  (* x y))
                (filter palindromic?)
                (last))
           906609))

(assert (true? (palindromic? 11)))
(assert (true? (palindromic? 101)))
(assert (true? (palindromic? 1001)))
(assert (false? (palindromic? 12345)))
(assert (true? (palindromic? 120021)))

