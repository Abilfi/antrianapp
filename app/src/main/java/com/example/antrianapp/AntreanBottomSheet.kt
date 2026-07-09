package com.example.antrianapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.card.MaterialCardView

class AntreanBottomSheet : BottomSheetDialogFragment() {

    // Mengaitkan class ini dengan layout XML yang kita buat di Langkah 2
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet_antrean, container, false)
    }

    // Mengatur logika tombol klik di dalam pop-up
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rajal = view.findViewById<MaterialCardView>(R.id.rawat_jalan)

        // Contoh: Jika user klik 'Faskes Rujukan Tingkat Lanjut', dia pindah ke PilihAntrianActivity
        rajal.setOnClickListener {
            val intent = Intent(requireContext(), AntrianActivity::class.java)
            startActivity(intent)
            dismiss() // Menutup pop-up otomatis setelah berpindah halaman
        }
    }
}