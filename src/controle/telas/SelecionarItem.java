package controle.telas;

import java.util.Optional;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Shell;

import controle.uteis.Interface;
import controle.uteis.Uteis;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SelecionarItem<E> {

	private Shell shell;
	private Shell pai;
	private Estocavel<E> selecionavel;
	private E selecionada;
	private boolean concluido = false;
	
	public SelecionarItem(Shell pai, Estocavel<E> selecionavel) {
		this.pai = pai;
		this.selecionavel = selecionavel;
		createContents();
		Interface.manterJanelaModal(shell);
	}

	private void createContents() {
		shell = new Shell(pai, SWT.DIALOG_TRIM);
		shell.setSize(360, 138);
		shell.setText("Selecione um item");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(8, 31, 339, 27);
		selecionavel.popularCombo(combo);
		
		Button btnSelecionar = new Button(shell, SWT.NONE);
		btnSelecionar.setText("Selecionar");
		btnSelecionar.setBounds(256, 64, 91, 23);
		btnSelecionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo.getSelectionIndex() >=0){
					selecionada = selecionavel.getLista().get(combo.getSelectionIndex());
					concluido = true;
					shell.close();
				} else{
					Uteis.exibirMensagem(combo, "Selecione um item!");
				}
			}
		});
		
		Button btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(256, 93, 91, 23);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
	}
	
	public Optional<E> getSelecionada() {
		return Optional.ofNullable(selecionada);
	}
	
	public boolean isConcluido() {
		return concluido;
	}
}
