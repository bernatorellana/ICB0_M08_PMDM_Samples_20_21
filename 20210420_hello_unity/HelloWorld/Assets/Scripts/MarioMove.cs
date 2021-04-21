using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MarioMove : MonoBehaviour
{

    public float velocitat;

    // Start is called before the first frame update
    void Start()
    {
              
    }

    // Update is called once per frame
    void Update()
    {

        float y = Input.GetAxis("Vertical");

        Vector3 novaPosicio = this.transform.position + new Vector3(0, 0, y*velocitat);
        this.transform.position = novaPosicio;
    }

    void FixedUpdate()
    {

    }
}
