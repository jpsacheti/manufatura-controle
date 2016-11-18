package controle.telas;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

import controle.uteis.Interface;
import controle.uteis.Uteis;

public class AjusteEstoque {

	private Shell pai;
	protected Shell shell;
	private BigDecimal quantidade;
	private boolean entrada;
	private boolean finalizou = false;

	public AjusteEstoque(Shell pai, String titulo, int digitos) {
		this.pai = pai;
		createContents(titulo, digitos);
		shell.open();
		Interface.manterJanelaModal(shell);
	}

	private void createContents(String titulo, int digitos) {
		shell = new Shell(pai, SWT.DIALOG_TRIM);
		shell.setSize(220, 153);
		shell.setText("Ajuste de estoque");
		
		Label lblItem = new Label(shell, SWT.NONE);
		lblItem.setBounds(13, 13, 279, 17);
		lblItem.setText(titulo);
		
		Button btnGravar = new Button(shell, SWT.NONE);
		btnGravar.setBounds(102, 96, 82, 27);
		btnGravar.setText("Gravar");
		
		
		Button btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(13, 96, 82, 27);
		btnCancelar.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(13, 36, 151, 27);
		spinner.setDigits(digitos);
		
		Button btnEntrada = new Button(shell, SWT.CHECK);
		btnEntrada.setBounds(13, 69, 104, 21);
		btnEntrada.setText("Entrada");
		btnEntrada.setSelection(true);
		
		btnGravar.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent arg0) {
				if(spinner.getSelection() == 0){
					Uteis.exibirMensagem(shell, "Selecione um valor!");
					spinner.forceFocus();
				} else{
					quantidade = Uteis.getSpinnerValueAsBigDecimal(spinner);
					entrada = btnEntrada.getSelection();
					finalizou = true;
					shell.close();
				}
			}
			
		});
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public boolean isEntrada() {
		return entrada;
	}

	public boolean isFinalizou() {
		return finalizou;
	}
}
