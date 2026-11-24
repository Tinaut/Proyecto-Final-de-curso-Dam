package com.example.projecto_final_curso.Adaptadores

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.projecto_final_curso.Modelo.ModeloImagenSeleccionada
import com.example.projecto_final_curso.R
import com.example.projecto_final_curso.databinding.ItemImagenesSeleccionadasBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AdaptadorImagenSeleccionada(
    private val context : Context,
    private val imagenesSelecArrayList : ArrayList<ModeloImagenSeleccionada>,
    private val idAnuncio : String
): Adapter<AdaptadorImagenSeleccionada.HolderImagenSeleccionada>(){

    private lateinit var binding : ItemImagenesSeleccionadasBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderImagenSeleccionada {
        binding = ItemImagenesSeleccionadasBinding.inflate(LayoutInflater.from(context),parent,false)
        return HolderImagenSeleccionada(binding.root)
    }

    override fun getItemCount(): Int {
        return imagenesSelecArrayList.size
    }

    override fun onBindViewHolder(holder: HolderImagenSeleccionada, position: Int) {
        val modelo = imagenesSelecArrayList[position]

        if (modelo.deInternet){
            /*Haremos lectura de las imágenes traidas desde Firebase*/
            try {
                val imagenUrl = modelo.imagenUrl
                Glide.with(context)
                    .load(imagenUrl)
                    .placeholder(R.drawable.item_imagen)
                    .into(binding.itemImagen)
            }catch (e: Exception){

            }
        }else{
            /*Haremos lectura de las imagenes seleccionadas desde la galería o tomadas desde la cámara*/
            try {
                val imagenUri = modelo.imagenUri
                Glide.with(context)
                    .load(imagenUri)
                    .placeholder(R.drawable.item_imagen)
                    .into(holder.item_imagen)
            }catch (e:Exception){

            }
        }



        holder.btn_cerrar.setOnClickListener {
            if (modelo.deInternet){
                //Declarar las vistas del diseño
                val Btn_si : MaterialButton
                val Btn_no : MaterialButton
                val dialog = Dialog(context)

                dialog.setContentView(R.layout.cuadro_d_eliminar_imagen)

                Btn_si = dialog.findViewById(R.id.Btn_si)
                Btn_no = dialog.findViewById(R.id.Btn_no)

                Btn_si.setOnClickListener {
                    eliminarImgFirebase(modelo, holder , position)
                    dialog.dismiss()
                }
                Btn_no.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()
                dialog.setCanceledOnTouchOutside(false)

            }else{
                imagenesSelecArrayList.remove(modelo)
                notifyDataSetChanged()
            }

        }
    }

    private fun eliminarImgFirebase(modelo: ModeloImagenSeleccionada, holder: AdaptadorImagenSeleccionada.HolderImagenSeleccionada, position: Int) {

        //Take the model fromA ModeloImagenSeleccionada

        val idImagen = modelo.id

        //The image will be deleted in the database
        val ref = FirebaseDatabase.getInstance().getReference("Anuncios")
        ref.child(idAnuncio).child("Imagenes").child(idImagen)
            .removeValue()
            .addOnSuccessListener {
                try {
                    imagenesSelecArrayList.remove(modelo)

                    //The image will also be removed from storage
                    eliminarImgStorage(modelo)
                    notifyItemRemoved(position)
                }catch (e:Exception){

                }
            }
            .addOnFailureListener {e->
                Toast.makeText(context, "${e.message}",Toast.LENGTH_SHORT).show()
            }
    }

    private fun eliminarImgStorage(modelo: ModeloImagenSeleccionada) {
        val rutaImagen = "Anuncios/"+modelo.id

        val ref = FirebaseStorage.getInstance().getReference(rutaImagen)
        ref.delete()
            .addOnSuccessListener {
                Toast.makeText(context, "La imagen se ha eliminado",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {e->
                Toast.makeText(context, "${e.message}",Toast.LENGTH_SHORT).show()
            }

    }

    inner class HolderImagenSeleccionada(itemView : View) : ViewHolder(itemView){
        var item_imagen = binding.itemImagen
        var btn_cerrar = binding.cerrarItem
    }


}