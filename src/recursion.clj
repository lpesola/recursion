(ns recursion)

(defn product [coll]
  (if (empty? coll)
    1 
    (* (first coll) (product (rest coll)))))

(defn singleton? [coll]
  (and (not (empty? coll)) (empty? (rest coll))))

(defn my-last [coll]
  (cond
    (empty? coll) nil
    (singleton? coll) (first coll)
    :else (my-last (rest coll))))

(defn max-element [a-seq]
  (if (empty? a-seq) 
    nil
    (if (singleton? a-seq)
      (first a-seq)  
      (max (first a-seq) (max-element (rest a-seq))))))

(defn seq-max [seq-1 seq-2]
  (cond 
    (> (count seq-1) (count seq-2)) seq-1
    :else seq-2))

(defn longest-sequence [a-seq]
  (if (empty? a-seq)
   nil
    (if (singleton? a-seq)
      (first a-seq)
      (seq-max (first a-seq) (longest-sequence (rest a-seq))) )))

(defn my-filter [pred? a-seq]
  (if (empty? a-seq)
   a-seq
   (if (pred? (first a-seq))
     (cons (first a-seq) (my-filter pred? (rest a-seq)))
     (my-filter pred? (rest a-seq)))))

(defn sequence-contains? [elem a-seq]
  (cond 
    (empty? a-seq) false
    (= elem (first a-seq)) true
    :else (sequence-contains? elem (rest a-seq))))

(defn my-take-while [pred? a-seq]
  (if (empty? a-seq)
    a-seq
   (if (pred? (first a-seq))
     (cons (first a-seq) (my-take-while pred? (rest a-seq)))
     [])))

(defn my-drop-while [pred? a-seq]
  (if (empty? a-seq)
   a-seq
   (if (pred? (first a-seq))
     (my-drop-while pred? (rest a-seq))
     a-seq)))

(defn seq= [a-seq b-seq]
 (cond 
  (and (empty? a-seq) (empty? b-seq)) true
  (or (and (empty? a-seq) (not (empty? b-seq))) 
      (and (empty? b-seq) (not (empty? a-seq)))) false
   :else (if (= (first a-seq) (first b-seq)) 
   (seq= (rest a-seq) (rest b-seq))
   false)))

(defn my-map [f seq-1 seq-2]
  (if (or (empty? seq-1) (empty? seq-2))
    []
    (cons (f (first seq-1) (first seq-2)) 
          (my-map f (rest seq-1) (rest seq-2)))))

(defn power [n k]
  (if (zero? k) 
   1
   (* n (power n (dec k)) )))

(defn fib [n]
  (cond 
   (= n 0) 0
   (= n 1) 1
   :else (+ (fib (dec n)) (fib (dec (dec n))))))


(defn my-repeat [how-many-times what-to-repeat]
  (if (<= how-many-times 0)
    []
    (cons what-to-repeat
          (my-repeat (dec how-many-times) what-to-repeat))))

(defn my-range [up-to]
  (if (== 0 up-to)
    []
    (cons (- up-to 1) (my-range (dec up-to)))))

(defn tails [a-seq]
  (if (empty? a-seq)
  [[]]
  (cons a-seq (tails (rest a-seq)))))

(defn inits [a-seq]
  (if (empty? a-seq)
  [[]]
  (cons a-seq (inits (reverse (rest (reverse a-seq))))) ))

(defn rotations-helper [a-seq depth]
  (if (<= depth 1)
   [a-seq] 
   (concat [a-seq] 
               (rotations-helper 
                 (concat (rest a-seq) [(first a-seq)]) (dec depth)))))

(defn rotations [a-seq]
 (rotations-helper a-seq (count a-seq)))


(defn my-frequencies-helper [freqs a-seq]
  (cond
    (empty? a-seq) freqs
    (contains? freqs (first a-seq)) 
      (my-frequencies-helper 
        (assoc freqs 
           (first a-seq) 
           (inc (get freqs (first a-seq)))) 
        (rest a-seq))
    :else (my-frequencies-helper 
               (assoc freqs (first a-seq) 1) (rest a-seq))))

(defn my-frequencies [a-seq]
 (my-frequencies-helper {} a-seq))

 (defn un-frequencies-helper [a-map a-seq]
  (cond
    (empty? a-map) a-seq
    :else (un-frequencies-helper (rest a-map)
               (concat a-seq 
               (repeat (first (rest (first a-map))) (first (first a-map)))))))
 
(defn un-frequencies [a-map]
  (un-frequencies-helper a-map ()))
  
(defn my-take-helper [n coll new-coll]
  (if (or (<= n 0) (empty? coll))
    new-coll
    (my-take-helper (dec n) (rest coll) (concat [(first coll)] new-coll))))
  
(defn my-take [n coll]
  (sort (my-take-helper n coll ())))

(defn my-drop [n coll]
  (if (<= n 0)
    coll
    (my-drop (dec n) (rest coll))))

(defn halve [a-seq]
  (let [half (int (/ (count a-seq) 2))]         
   [(my-take half a-seq) (my-drop half a-seq)]))

(defn seq-merge-helper [a-seq b-seq new-seq]
  (cond
    (and (empty? a-seq) (empty? b-seq)) new-seq
    (empty? a-seq) (concat new-seq b-seq)
    (empty? b-seq) (concat new-seq a-seq)
    :else (cond
      (== (first a-seq) (first b-seq)) 
	(seq-merge-helper (rest a-seq) (rest b-seq)
	  (concat new-seq [(first a-seq)] [(first b-seq)]))
      (> (first a-seq) (first b-seq)) 
	(seq-merge-helper a-seq (rest b-seq) 
	  (concat new-seq [(first b-seq)]))
      (< (first a-seq) (first b-seq))
	(seq-merge-helper (rest a-seq) b-seq 
	  (concat new-seq [(first a-seq)])))))

(defn seq-merge [a-seq b-seq]
  (seq-merge-helper a-seq b-seq ()))

(defn merge-sort [a-seq]
  (if (or (empty? a-seq) (== 1 (count a-seq))) 
    a-seq
    (let [halves (halve a-seq)]
    (seq-merge (merge-sort (get halves 0) ) (merge-sort (get halves 1)) )) ))

(defn split-into-monotonics [a-seq]
  [:-])

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])

