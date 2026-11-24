# Benchmark: Section 5 results

All money is in INR. Quantities and money use `BigDecimal` in code.

## Results we will check

| Check | Expected | How I calculate it |
| --- | --- | --- |
| Flour closing balance | 120 kg | 100 opening + 60 purchased − 40 issued = 120 |
| Oil closing balance | 19.5 L | 30 opening − 10.5 issued = 19.5 |
| Pouches closing balance | 306 | 500 opening − 188 used − 6 damaged = 306 |
| Finished goods after run | 238 packets | 50 opening + 188 good output = 238 |
| Finished goods after Retailer A dispatch | 88 packets | 238 − 150 = 88 |
| Yield variance | 188 vs 200, −6%, flagged | (188 − 200) / 200 × 100 = −6% |
| Oil consumption variance | +5%, flagged | (10.5 − 10) / 10 × 100 = +5% |
| Retailer A invoice | ₹7,500 + ₹187.50 CGST + ₹187.50 SGST = ₹7,875 | taxable = 150 × 50 = 7,500; GST 5% = 375; CGST 2.5% = 187.50; SGST 2.5% = 187.50; total = 7,875 |
| Retailer A outstanding | ₹3,875 | invoice total 7,875 − payment 4,000 = 3,875 |
| Distributor B order | Shortage of 32 packets; IGST if invoiced | needed 120 − available 88 = 32 short; Karnataka is inter-state, so IGST 5% (not split) |
| Cost per packet | ₹31.14 | see Run cost below |

My numbers match the expected values in every row.

## Run cost (Murukku 200 g, 1 run)

Valuation method: **weighted average**.

| Item | Qty | Rate (₹) | Cost (₹) |
| --- | --- | --- | --- |
| Flour | 40 kg | 43.125 | 1,725.00 |
| Cooking oil | 10.5 L | 140 | 1,470.00 |
| Salt and spice mix | 1.2 kg | 200 | 240.00 |
| Pouches (188 good + 6 damaged) | 194 | 2.50 | 485.00 |
| Cartons | 9 | 15 | 135.00 |
| **Raw + packaging total** | | | **4,055.00** |
| Labour | | | 1,200.00 |
| Gas / power | | | 600.00 |
| **Total run cost** | | | **5,855.00** |

Cost per packet = 5,855 ÷ 188 good packets = **₹31.14**
Margin against selling price = 50 − 31.14 = **₹18.86 per packet** (about 37.7%).

## Flour valuation: both methods compared

| Method | Calculation for 40 kg | Flour cost | Closing flour value (120 kg) |
| --- | --- | --- | --- |
| FIFO | 40 kg × ₹42 (oldest stock first) | 1,680 | 6,900 − 1,680 = 5,220 |
| Weighted average | (100 × 42 + 60 × 45) / 160 = 6,900 / 160 = ₹43.125 per kg, then × 40 | 1,725 | 6,900 − 1,725 = 5,175 |

FIFO gives cost per packet ₹30.90 (5,810 ÷ 188). Weighted average gives ₹31.14. The difference is ₹0.24 per packet.

**My choice: weighted average**, because the stock ledger only needs to keep one running rate per item, recalculated on each purchase, instead of tracking which purchase lots are still left. It is also simple to explain to an auditor, and it avoids the cost jumping between runs when one lot runs out. FIFO may be added later if batch and expiry tracking is built.

## Divisor for cost per packet

I divide by **188 good packets**, not the expected 200. The full run cost was spent whether or not the 12 missing packets existed. Dividing by 200 would hide the cost of wastage and damaged pouches and make the product look cheaper than it is. Dividing by 188 puts that loss into the cost of each sellable packet, and the −6% yield variance flag shows why it is high.

## Overhead rule

Labour (₹1,200) and gas/power (₹600) are recorded against this run, so the full ₹1,800 goes to it. In a real month with many runs, I would allocate monthly overheads to products in proportion to good packets produced, because it is simple and needs no machine-hour data.

## Assumptions

- Selling price is ₹50 per packet before tax, GST is 5%, and the factory is in Tamil Nadu. Retailer A is in Tamil Nadu (CGST + SGST). Distributor B is in Karnataka (IGST).
- Damaged pouches (6) are real cost, so the pouch cost counts 194 pouches. The closing pouch balance is 306.
- The brief gives 9 cartons used. Each carton holds 20 packets, so 9 cartons cover 180 packets. The other 8 good packets are treated as loose stock, and the carton count is used as given.
- Distributor B ordered 120 but only 88 packets are available. The system flags a shortage of 32 and does not auto-approve. The admin can approve a partial quantity of 88, wait for the next run, or reject the order.
- Flour, spice and cartons have no variance beyond tolerance. Oil (+5%) and yield (−6%) are flagged against a 3% tolerance.